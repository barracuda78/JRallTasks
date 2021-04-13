package com.javarush.task.task18.task1807;

/* Подсчет запятых
С консоли считать имя файла.
Посчитать в файле количество символов ',', количество вывести на консоль.
Закрыть потоки.

Подсказка:
нужно сравнивать с ascii-кодом символа ','.

Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль должно выводится число запятых в считанном файле.
4. Поток чтения из файла должен быть закрыт.*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        System.out.println(countOfCharacters(bytesToArrayList(readFileName())));
    }


    //метод для чтения имени файла с консоли.
    public static String readFileName(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        try {
            fileName = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    //метод для записи байтов из файла в ArrayList.
    public static ArrayList<Integer> bytesToArrayList(String fileName){
        ArrayList<Integer> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            while(fis.available() > 0){
                list.add(fis.read());
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    //метод для подсчета ' в листе.
    public static int countOfCharacters(ArrayList<Integer> list){
        int count = 0;
        for(Integer byteInt : list){
            if(byteInt == 44){
                count++;
            }
        }

        return count;
    }
}
