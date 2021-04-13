package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Генератор паролей
*/

public class Solution {
//    public static void main(String[] args) {
//        ByteArrayOutputStream password = getPassword();
//        System.out.println(password.toString());
//    }
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            ByteArrayOutputStream password = getPassword();
            String s = password.toString();
            if (!s.matches(".*\\d.*")) {
                System.out.println(s + " - нет цифры");

            }
            if (!s.matches(".*[a-z].*")) {
                System.out.println(s + " - нет строчной буквы");

            }
        }
    }

    public static ByteArrayOutputStream getPassword() {
        //48 - 57  - иапазон цифр - коды unicode
        //65 - 90  - диапазон латиница - заглавные.
        //97 - 122 - диапазон латиница - маленькие

        //количество цифр в пароле:
        int countNumbers = (int)(Math.random() * 5 + 1); //от 1 до 6

        //количество заглавных букв в пароле:
        int countLettersCaps = (int)(Math.random() * 5 + 1); //от 1 до 6

        //количество строчных букв в пароле:
        int countLettersLow = (int)(Math.random() * 5 + 1); //от 1 до 6

        //сумма цифр и заглавных букв не должна превышать 7 (оставить место для хотя бы одной строчной буквы)
        while((countLettersCaps + countNumbers) > 7){
            //случайная величина boolean - если true - уменьшаем countNumbers, иначе уменьшаем countLettersCaps
            boolean choice;
            int n = (int)(Math.random() * 10) ;
            if(n%2 == 0){
                choice = true;
            }
            else{
                choice = false;
            }

            if(choice){
                //уменьшаем countNumbers
                if (countNumbers == 1)
                    continue;
                countNumbers--;
            }else{
                //уменьшаем
                if (countLettersCaps == 1)
                    continue;
                countLettersCaps--;
            }
        }

        //сумма всех символов не должна превышать 8:
        while(countLettersCaps + countNumbers + countLettersLow > 8){
            int choice = (int)(Math.random() * 3) ;
            switch (choice){
                case 0:
                    if(countLettersCaps > 1){
                        countLettersCaps--;
                    }
                    break;
                case 1:
                    if(countNumbers > 1){
                        countNumbers--;
                    }
                    break;
                case 2:
                    if(countLettersLow > 1){
                        countLettersLow--;
                    }
                    break;
            }
        }

        //если символов меньше восьми - добавить каждому по-чутка в цикле.
        while(countLettersCaps + countNumbers + countLettersLow < 8){
            int choice = (int)(Math.random() * 3) ;
            switch (choice){
                case 0:
                    countLettersCaps++;
                    break;
                case 1:
                    countNumbers++;
                    break;
                case 2:
                    countLettersLow++;
                    break;
            }
        }

        //48 - 57  - диапазон цифр - коды unicode
        //массив кодов символов цифр
        char[] numbers = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57};

        //массив кодов заглавных букв:
        //65 - 90  - диапазон латиница - заглавные.
        char[] capLetters = new char[26];
        int index = 0;
        for(int i = 65; i <= 90; i++){
            capLetters[index] = (char)i;
            index ++;
        }

        //массив кодов строчных букв
        //97 - 122 - диапазон латиница - маленькие
        char[] lowLetters = new char[26];
        index = 0;
        for(int i = 97; i <= 122; i++){
            lowLetters[index] = (char)i;
            index ++;
        }

        //массив символов пароля:
        Character[] symbols = new Character[8];

        //1. заполнить произвольными цифрами из массива numbers в размере countNumbers:
        for(int i = 0; i < countNumbers; i++){
            char randomChar = numbers[(int)(Math.random() * numbers.length)];
            symbols[i] = randomChar;
        }


        //2. заполнить произвольными заглавными буквами в размере countLettersCaps:
        for(int i = countNumbers; i < countNumbers + countLettersCaps; i++){
            char randomChar = capLetters[(int)(Math.random() * capLetters.length)];
            symbols[i] = randomChar;
        }


        //3. заполнить произвольными строчными буквами в размере countLettersLow:
        for(int i = countNumbers + countLettersCaps; i < 8; i++){
            char randomChar = lowLetters[(int)(Math.random() * lowLetters.length)];
            symbols[i] = randomChar;
        }


        //4. перегнать в коллекцию и сделать shuffle();
        List<Character> list = Arrays.asList(symbols);
        Collections.shuffle(list);

        //5. перегнать в ByteArrayOutputStream и вернуть
        StringBuilder sb = new StringBuilder();
        for(Character c : list){
            sb.append(c);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            byteArrayOutputStream.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return byteArrayOutputStream;
    }
}
