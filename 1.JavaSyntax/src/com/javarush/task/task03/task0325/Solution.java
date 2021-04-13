package com.javarush.task.task03.task0325;

import java.io.*;

/* 
Финансовые ожидания
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String salary = bufferedReader.readLine(); //читаем строку с клавиатуры
        int n = Integer.parseInt(salary); //преобразовываем строку в число.

        System.out.println("Я буду зарабатывать $" + n + " в час");
    }
}
