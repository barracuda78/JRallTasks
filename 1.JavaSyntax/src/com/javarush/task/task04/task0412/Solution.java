package com.javarush.task.task04.task0412;

/* 
Положительное и отрицательное число
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String numer = bufferedReader.readLine(); //читаем строку с клавиатуры
        int num = Integer.parseInt(numer); //преобразовываем строку в число.

        if(num > 0) {
            num = num*2;
            System.out.println(num);
        }
        else {
            if(num < 0) {
                num = num+1;
                System.out.println(num);
            }
            else
                System.out.println(num);
        }
    }

}