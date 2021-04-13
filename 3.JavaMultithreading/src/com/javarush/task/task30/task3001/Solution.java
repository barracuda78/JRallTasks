package com.javarush.task.task30.task3001;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Конвертер систем счислений
*/

public class Solution {
    public static void main(String[] args) {
        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef
    }

    //Тестовый метод main - закомментить перед валидацией!!!
//    public static void main(String[] args) {
//        Pattern p = Pattern.compile(createRegEx(16));
//
//        Matcher m = p.matcher("99af");
//
//        System.out.println("Регулярка: " + p);
//
//        System.out.println("Соответствие: " + m.matches());
//    }

    public static Number convertNumberToOtherNumberSystem(Number number, NumberSystem expectedNumberSystem) {
        //первый параметр метода convertNumberToOtherNumberSystem:
        //number - число, у которого есть:
        //          - тип системы счисления, например NumberSystemType._16
        //          - само число в виде строки, например, 6df
        //второй параметр метода convertNumberToOtherNumberSystem:
        //expectedNumberSystem - ожидаемая система счисления для перевода в неё заданного числа.
        //алгоритм:

        //0. проверка регулярками для каждой системы счисления:
        if(!isNumberCorrect(number))
            throw new NumberFormatException();
        //1. определяем систему счисления числа number, записываем в инт.
        int currentSys = number.getNumberSystem().getNumberSystemIntValue();

        //2. определяем сислему счисления для перевода, записываем в инт.
        int expectedSys = expectedNumberSystem.getNumberSystemIntValue();

        //3. получаем строковое представление числа number:
        String numString = number.getDigit();

        BigInteger a = new BigInteger(numString, number.getNumberSystem().getNumberSystemIntValue());
        String expectedString = a.toString(expectedSys);
        return new Number(expectedNumberSystem, expectedString);

        //4. сравниваем,
        //4.1              если currentSys < expectedSys -> тогда алгоритм возведения в степени.
        //4.2              если currentSys > expectedSys -> тогда алгоритм деления с остатком.
        //4.3              если currentSys == expectedSys -> тогда возвращаем Number number.
//        if(currentSys < expectedSys){
//            //разряды:      7 6 5 4 3 2 1 0
//            //currentSys == 0 0 0 1 0 0 0 1
//
//            String expectedString = "";
//            return new Number(expectedNumberSystem, expectedString);
//
//        }else if(currentSys > expectedSys){
//
//        }else{
//            return number;
//        }

    }

    //вспомогательный метод для получения разряда числа Number number:
    //private static

    //вспомогательный метод для понимания, соответствует ли number - корректное число в этой системе.
    private static boolean isNumberCorrect(Number number){
        Pattern p = Pattern.compile(createRegEx(number.getNumberSystem().getNumberSystemIntValue()));
        Matcher m = p.matcher(number.getDigit());
        return m.matches();
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
