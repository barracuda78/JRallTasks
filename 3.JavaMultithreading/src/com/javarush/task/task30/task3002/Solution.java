package com.javarush.task.task30.task3002;

/* 
Осваиваем методы класса Integer
*/

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    private static int radix;

    public static void main(String[] args) {
        System.out.println(convertToDecimalSystem("0x16")); //22
        System.out.println(convertToDecimalSystem("012"));  //10
        System.out.println(convertToDecimalSystem("0b10")); //2
        System.out.println(convertToDecimalSystem("62"));   //62

//        String number = "012";
//        System.out.println(number.substring(0,1).equals("0"));
//        System.out.println(number.substring(1));
//        System.out.println(Integer.parseInt("12", 8));
    }

    //парсит в десятичную методом Integer.parseInt(String, int)
    //парсим 0x16 - нужно отрезать 0x.
    public static String convertToDecimalSystem(String s) {
        String number = numberCorrection(s);

        int n = Integer.parseInt(number, radix);

        //напишите тут ваш код
        return String.valueOf(n);
    }

    private static String numberCorrection(String number){
        //паттерн для 10-чного и 8-чного числа.
        Pattern pattern01 = Pattern.compile("[0-9]+");
        Matcher matcher01 = pattern01.matcher(number);

        //если number состоит только из цифр - значит 8-чное или 10-чное.
        if(matcher01.matches()){
            if(number.substring(0,1).equals("0")) {
                radix = 8;
                return number.substring(1);
            }else {
                radix = 10;
                return number;
            }
            //return number.substring(0,1).equals("0") ? number.substring(1) : number;
        }

        //паттерн для 16-ричного числа: "0x16"
        Pattern pattern02 = Pattern.compile("0[xX][0-9a-fA-F]+");
        Matcher matcher02 = pattern02.matcher(number);

        //если начинается на 0x или 0X
        if(matcher02.matches()){
            radix = 16;
            return number.substring(2);
        }

        //паттерн для двоичного числа: "0b100101"
        Pattern pattern03 = Pattern.compile("0[bB][01]+");
        Matcher matcher03 = pattern03.matcher(number);

        //если начинается на 0b или 0B
        if(matcher03.matches()){
            radix = 2;
            return number.substring(2);
        }

        throw new NumberFormatException();
    }
}
