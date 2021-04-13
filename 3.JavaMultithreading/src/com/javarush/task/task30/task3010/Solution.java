package com.javarush.task.task30.task3010;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Минимальное допустимое основание системы счисления
*/

public class Solution {
    public static void main(String[] args) {
        try {
            //String arg = "00";    //--------------------------------> Заменить на args в настройках IDEA перед валидацией.

            Pattern p = Pattern.compile("[0-9a-zA-Z]+");
            Matcher m = p.matcher(args[0]);

            if (!m.matches() || args[0].length() > 255) {
                System.out.println("incorrect");
                return;
            }

            //i - основание системы счисления.

            boolean stringMatches = false;
            for (int i = 2; i < 37; i++) {

                //System.out.println("Основание = " + i + " : " + createRegEx(i));  -----> ТЕСТ регулярок.

                //сравнимаем строку из агрумета с паттерном заданной системы счисления:
                Pattern n = Pattern.compile(createRegEx(i));
                Matcher matcher = n.matcher(args[0]);

                //если совпадает - - нужно вывести минимальное основание системы счисления,
                // в которой это число может существовать. То есть i.
                if (matcher.matches()) {
                    System.out.println(i);
                    stringMatches = true;
                    break;
                }
            }

            //Если не является - необходимо вывести "incorrect".
            if (!stringMatches) {
                System.out.println("incorrect");
            }

        }catch(Exception e){

        }
        //напишите тут ваш код
    }

    //вспомогательный метод для написания регулярки под заданную систему счисления:
    private static String createRegEx(int currentSys){
        String regEx = "[";

        StringBuilder sb = new StringBuilder(regEx);
        if(currentSys <= 10) {
            for (int i = 0; i < currentSys; i++)
                sb.append(i);
        }else{
            for (int i = 0; i < 10; i++)
                sb.append(i);

            char c = 65;
            for(int j = 10; j < currentSys; j++){
                sb.append(c);
                c++;
            }

            char ch = 97;
            for(int j = 10; j < currentSys; j++){
                sb.append(ch);
                ch++;
            }
        }
        sb.append("]*");
        return sb.toString();
    }
}