package com.javarush.task.task20.task2027;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
//если длина слова 1 буква, то в конце добавить, чтобы с одними начальными координатами она осталась только одна.
/*
Кроссворд
//if (j < 1 + crossword[i].length - word.length() && crossword[i][j] == charArray[0] && crossword[i][j + 1] == charArray[1] && crossword[i][j + 2] == charArray[2] && crossword[i][j + 3] == charArray[3]) {
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
//                {'f', 'd', 'e', 'r', 'l', 'k'},
//                {'u', 's', 'a', 'm', 'e', 'o'},
//                {'l', 'n', 'g', 'r', 'o', 'v'},
//                {'m', 'l', 'p', 'r', 'r', 'h'},
//                {'p', 'o', 'e', 'e', 'j', 'j'}

                // 0    1    2    3    4    5
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f'},    // 0
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f'},    // 1
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f'},    // 2
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f'},    // 3
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'e'},
                {'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 's'}
        };
        detectAllWords(crossword, "home", "same", "sex"); //-----------------> ошибка с словом из 3-х букв
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */

    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        //Создадим ArrayList для хранения Words:
        List<Word> list = new ArrayList<>();

        for(String word : words) {
            char[] charArray = word.toCharArray();
            //Если слово состоит из одной буквы, его не нужно искать во все 8 направлений, т.к. у него координаты начала == координатам конца, и оно просто отобразится 8 раз в ArrayList<>, а это неправильно.
            //Добавление слов из одной буквы ( у которых нет направления, т.е. все 8 направлений равны))
            if(charArray.length == 1){
                for (int i = 0; i < crossword.length; i++) {
                    for (int j = 0; j < crossword[i].length - word.length() + 1; j++) {

                        if (crossword[i][j] == charArray[0]) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j, i);
                            list.add(w);
                        }
                    }
                }
            }
            else {
                //Добавление слов из 2-х и более букв, по всем 8-ми направлениям:

                // -----------------------------1. по горизонтали слева направо:----------------------DONE!!!!!!!!!!!!!!
                //4 буквы: j <= 2
                //3 буквы: j <= 3
                //2 буквы: j <= 4
                //1 буквы: j <= 5
                for (int i = 0; i < crossword.length; i++) {
                    for (int j = 0; j <= crossword[i].length - word.length(); j++) {

                        int jCursor = j;
                        int counter = 0;
                        for (int ltr = 0; ltr < charArray.length; ltr++) {
                            if (crossword[i][jCursor] == charArray[ltr]) {
                                counter++;
                                jCursor++;
                            }
                        }
                        if (counter == word.length()) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j + word.length() - 1, i);
                            list.add(w);
                        }
                    }
                }

                // -----------------------------2. по горизонтали справа налево:----------------------DONE!!!!!!!!!!!!!!
                // 4 буквы: j >= 3
                // 3 буквы: j >= 2
                // 2 буквы: j >= 1

                for (int i = crossword.length - 1; i >= 0; i--) {
                    for (int j = crossword[i].length - 1; j >= word.length() - 1; j--) {
                        int jCursor = j;
                        int counter = 0;
                        for (int ltr = 0; ltr < charArray.length; ltr++) {
                            if (crossword[i][jCursor] == charArray[ltr]) {
                                counter++;
                                jCursor--;
                            }
                        }
                        if (counter == word.length()) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j - word.length() + 1, i);
                            list.add(w);
                        }
                    }
                }

                // -----------------------------3. по вертикали сверху вниз:------------------------DONE!!!!!!!!!!!!!!
                // 4 буквы: i <= 1: j <=
                // 3 буквы: i <= 2
                // 2 буквы: i <= 3
                // 1 буквы: i <= 4
                for (int i = 0; i <= crossword.length - word.length(); i++) {
                    for (int j = 0; j < crossword[i].length; j++) {

                        int iCursor = i;
                        int counter = 0;
                        for (int ltr = 0; ltr < charArray.length; ltr++) {
                            if (crossword[iCursor][j] == charArray[ltr]) {
                                counter++;
                                iCursor++;
                            }
                        }
                        if (counter == word.length()) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j, i + word.length() - 1);
                            list.add(w);
                        }
                    }
                }
//                // 0    1    2    3    4    5
//                {'x', 'f', 'f', 'f', 'f', 'f'},    // 0
//                {'e', 'f', 'f', 'f', 'f', 'f'},    // 1
//                {'s', 'f', 'f', 'f', 'f', 'f'},    // 2
//                {'f', 'f', 'f', 'f', 'f', 'f'},    // 3
//                {'f', 'f', 'f', 'f', 'f', 'f'}     // 4
                // -------------------------------4. по вертикали снизу ввверх----------------------
                // 4 буквы: i >= 3
                // 3 буквы: i >= 2
                // 2 буквы: i >= 1

                for (int i = crossword.length - 1; i >= word.length() - 1; i--) {
                    for (int j = crossword[i].length - 1; j >= 0; j--) {

                        int iCursor = i;
                        int counter = 0;
                        for (int ltr = 0; ltr < charArray.length; ltr++) {
                            if (crossword[iCursor][j] == charArray[ltr]) {
                                counter++;
                                iCursor--;
                            }
                        }
                        if (counter == word.length()) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j, i - word.length() + 1);
                            list.add(w);
                        }
                    }
                }



                // -------------------------------5. по диагонали сверху вниз слева направо-------------DONE!!!!!!!!!!!!!!
                //4 буквы: j <= 2;  i <= 1
                //3 буквы: j <= 3;  i <= 2
                //2 буквы: j <= 4;  i <= 3
                //1 буквы: j <= 5;  i <= 4
                for (int i = 0; i <= crossword.length - word.length(); i++) {
                    for (int j = 0; j <= crossword[i].length - word.length(); j++) {

                        int iCursor = i;
                        int jCursor = j;
                        int counter = 0;
                        for (int ltr = 0; ltr < charArray.length; ltr++) {
                            if (crossword[iCursor][jCursor] == charArray[ltr]) {
                                counter++;
                                iCursor++;
                                jCursor++;
                            }
                        }
                        if (counter == word.length()) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j + word.length() - 1, i + word.length() - 1);
                            list.add(w);
                        }
                    }
                }

                // -------------------------------6. по диагонали снизу вверх справа налево----------DONE!!!!!!!!!!!!!!
                for (int i = crossword.length - 1; i >= word.length() - 1; i--) {
                    for (int j = crossword[i].length - 1; j >= word.length() - 1; j--) {

                        int iCursor = i;
                        int jCursor = j;
                        int counter = 0;
                        for (int ltr = 0; ltr < charArray.length; ltr++) {
                            if (crossword[iCursor][jCursor] == charArray[ltr]) {
                                counter++;
                                iCursor--;
                                jCursor--;
                            }
                        }
                        if (counter == word.length()) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j - word.length() + 1, i - word.length() + 1);
                            list.add(w);
                        }
                    }
                }


                // -------------------------------7. по диагонали сверху вниз справа налево-----------DONE!!!!!!!!!!!!!!
                // 4 буквы: i <= 1;  j >= 3
                // 3 буквы: i <= 2;  j >= 2
                // 2 буквы: i <= 3;  j >= 1
                // 1 буквы: i <= 4;  j >= 0

                for (int i = 0; i <= crossword.length - word.length(); i++) {
                    for (int j = crossword[i].length - 1; j >= word.length() - 1; j--) {
                        int iCursor = i;
                        int jCursor = j;
                        int counter = 0;
                        for (int ltr = 0; ltr < charArray.length; ltr++) {
                            if (crossword[iCursor][jCursor] == charArray[ltr]) {
                                counter++;
                                iCursor++;
                                jCursor--;
                            }
                        }
                        if (counter == word.length()) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j - word.length() + 1, i + word.length() - 1);
                            list.add(w);
                        }
                    }
                }


                // -------------------------------8. по диагонали снизу вверх слева направо-----------DONE!!!!!!!!!!!!!!
                //  4 буквы: i >= 3; j <= 2
                //  3 буквы: i >= 2; j <= 3
                //  2 буквы: i >= 1; j <= 4
                //  1 буквы: i >= 0; j <= 5

                for (int i = crossword.length - 1; i >= word.length() - 1; i--) {
                    for (int j = 0; j <= crossword[i].length - word.length(); j++) {
                        int iCursor = i;
                        int jCursor = j;
                        int counter = 0;
                        for (int ltr = 0; ltr < charArray.length; ltr++) {
                            if (crossword[iCursor][jCursor] == charArray[ltr]) {
                                counter++;
                                iCursor--;
                                jCursor++;
                            }
                        }
                        if (counter == word.length()) {
                            Word w = new Word(word);
                            w.setStartPoint(j, i);
                            w.setEndPoint(j + word.length() - 1, i - word.length() + 1);
                            list.add(w);
                        }
                    }
                }
            }
        }

        System.out.println(list);
        System.out.println("Размер = " + list.size());
        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}

//                       // 0    1    2    3    4    5
//                        {'f', 'f', 'f', 'f', 'f', 'f'},    // 0
//                        {'f', 'f', 'f', 'f', 'f', 'f'},    // 1
//                        {'f', 'f', 'f', 'f', 'f', 'f'},    // 2
//                        {'f', 'f', 'f', 'f', 'f', 'f'},    // 3
//                        {'f', 'f', 'f', 'f', 'f', 'f'}     // 4

