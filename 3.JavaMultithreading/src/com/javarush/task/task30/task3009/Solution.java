package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output:
//        System.out.println(7/2);
    }

    private static Set<Integer> getRadix (String number){
        Set<Integer> set = new HashSet<>();
        try{
            //проверка переданного числа на корректность:
            Pattern pCorrectNumber10 = Pattern.compile("[0-9]+");
            Matcher mCorrectNumber10 = pCorrectNumber10.matcher(number);

            //Если переданное число некорректно - возвращай пустой HashSet.
            if(!mCorrectNumber10.matches())
                return new HashSet<Integer>();

            //цикл итерации по системам счисления от 2 до 36
            //BigInteger
            //проверка на палиндром.
            for(int i = 2; i < 37; i++){
                BigInteger a = new BigInteger(number, 10);
                //        String expectedString = a.toString(expectedSys);
                //        return new Number(expectedNumberSystem, expectedString);
                //получили строковое представление числа в системе счисления i:
                String s = a.toString(i);
                //s = 111 0 000
                //s = 111 000
                //проверим cтроку на палиндром:
                char[] chars = s.toCharArray();
                int counter = 0;
                for(int j = 0; j < chars.length; j++){
                    if(j == chars.length/2)
                        break;

                    if(chars[j] == chars[chars.length - j - 1]){
                        counter++;
                    }
                }

                if(counter == chars.length/2){
                    //set.add(Integer.parseInt(s));
                    set.add(i);
                }
            }



        }catch(NumberFormatException ne){
            return new HashSet<Integer>();
        }
        return set;
    }
}