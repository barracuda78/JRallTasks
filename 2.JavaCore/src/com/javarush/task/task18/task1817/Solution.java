package com.javarush.task.task18.task1817;

/*
Английские буквы
*/

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Solution {


    public static void main(String[] args) throws Exception {

       // String fileName = args[0];
        String fileName = "C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1816\\test.txt";

        FileInputStream inputStream = new FileInputStream(fileName);

        ArrayList<Integer> array = new ArrayList<>();

        while (inputStream.available() > 0) {
            int data = inputStream.read();

            array.add(data);
        }

        inputStream.close();

        double count = 0;

        for (int i = 0; i < array.size(); i++)

        {

            if ((array.get(i) == 32)) {
                count++;
            }

        }
        double size = array.size();

        double fin = (count / size) * 100;
        System.out.println(new BigDecimal(fin).setScale(2, RoundingMode.HALF_UP).floatValue());
    }
}