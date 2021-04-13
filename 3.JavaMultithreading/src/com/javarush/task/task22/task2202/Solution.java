package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
        //System.out.println(getPartOfString("Амиго и Диего лучшиедрузья!"));
    }

    public static String getPartOfString(String string) {
        //0. Если string == null -> throw new TooShortStringException();
        //1. Методом split() разделить строку string на массив строк.
        // 1.1.Если в массиве будет 5 строк (то есть 4 разделяющих пробела), значит
        //String s = string.substring(string.indexOf(" ")+1); - без второго аргумента, то есть до конца строки.
        // 1.2. Если в массиве будет больше 5-ти строк (то есть более 4-х разделяющих пробелов), значит
        //String s = string.substring(string.indexOf(" ")+, x); - со вторым агруметом x, который является индексом пятого пробела минус 1.
        //Индекс пятого пробела вычислил в цикле итерируяст по char[] chArray = string.toCharArray();

        // 1.3. Если в массиве будет 4 или менее строки, то значит у нас только 3 или менее пробела, нужно кидать Exception.
        // 2. public static class TooShortStringException extends RuntimeException
        if (string == null) throw new TooShortStringException();

        String s = "";

        String[] sArray = string.split(" ");
        if(sArray.length == 5){
            s = string.substring(string.indexOf(" ")+1);
        }
        else if(sArray.length > 5){
            char[] chArray = string.toCharArray();
            int fifthSpaceIndex = -1; //это будет индекс пятого пробела. А так же это индекс конца слова, которое следует после 4-го пробела
            int counter = 0; // счетчик встретившихся в строке пробелов

            for (int i = 0; i < chArray.length; i++) {
                if (chArray[i] == ' ') {
                    counter++;
                    if (counter == 6) break;
                    fifthSpaceIndex = i;
                }
            }
            s = string.substring(string.indexOf(" ")+1, fifthSpaceIndex);
        }
        else{
            throw new TooShortStringException();
        }
        //Или лучше создать 4 подстроки, обрезая с первого пробела. И мерять длину каждой троки, складывать, пока не получится 4 пробела. Так найти индекс 4го пробела.
        return s;
    }
    public static class TooShortStringException extends RuntimeException {

    }
}
