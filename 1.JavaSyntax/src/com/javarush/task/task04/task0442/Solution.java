package com.javarush.task.task04.task0442;


/* 
Суммирование
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        int sum = 0;
        while(true) {
            InputStream inputStream = System.in;
            Reader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String numbers = bufferedReader.readLine();
            int number = Integer.parseInt(numbers);

            sum += number;

            if (number == -1) {
                System.out.println(sum);
                break;
            }
        }
    }
}
