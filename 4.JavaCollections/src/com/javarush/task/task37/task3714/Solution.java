package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
//Древний Рим
//Амиго, привет! Я недавно увлекся историей вашей планеты и меня заинтересовал период Древнего Рима.
//Интересно тогда было жить, сплошные развлечения и вино! Или рабство, если не повезло со стартовой локацией...
//
//В общем, мне нужен метод romanToInteger, который будет конвертировать число в римской системе счисления {I, V, X, L, C, D, M} в десятичную. Иначе никак не разобрать что и когда у них происходило.
//
//
//Требования:
//1. Метод romanToInteger должен конвертировать число в римской системе счисления в десятичную.
//2. Метод romanToInteger должен возвращать значение типа int и принимать один параметр типа String.
//3. Метод romanToInteger должен быть публичным.
//4. Метод romanToInteger должен быть статическим.
public class Solution {
    public static HashMap<Character, Integer> map = new HashMap<>();
    static{
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        s = s.toUpperCase();
        char[] array = s.toCharArray();
        int currentNumber;
        int result = 0;

        for (int i = array.length; i > 1; i -= 2) {
            int right = map.get(array[i - 1]);
            int left = map.get(array[i - 2]);
            if (right > left) {
                currentNumber = right - left;
            } else {
                currentNumber = right + left;
            }
            result += currentNumber;
        }

        if (array.length % 2 == 0) {
            return result;
        } else {
            result += map.get(array[0]);
            return result;
        }


    }
}