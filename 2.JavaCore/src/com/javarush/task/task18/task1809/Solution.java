package com.javarush.task.task18.task1809;

/* 
Реверс файла
Считать с консоли 2 имени файла: файл1, файл2.
Записать в файл2 все байты из файл1, но в обратном порядке.
Закрыть потоки.

Требования:
1. Программа должна два раза считать имена файлов с консоли.
2. Для чтения из файла используй поток FileInputStream, для записи в файл - FileOutputStream
3. Во второй файл нужно записать все байты из первого в обратном порядке.
4. Потоки FileInputStream и FileOutputStream должны быть закрыты.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    static String fileName1 = null;
    static String fileName2 = null;
    public static void main(String[] args) {
        readFileNames();
        readBytes(fileName1, fileName2);
    }
    public static void readFileNames(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            fileName1 = bufferedReader.readLine();
            fileName2 = bufferedReader.readLine();
            bufferedReader.close();
        }
        catch (IOException i){
            i.printStackTrace();
        }

    }

    public static void readBytes(String fileName1, String fileName2){
        ArrayList<Integer> list = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName1);
            FileOutputStream fos = new FileOutputStream(fileName2);
            while(fis.available() > 0){
                int temp = fis.read();
                list.add(temp);
            }
            for(int i = list.size(); i > 0; i--){
                fos.write(list.get(i-1));
            }

            fis.close();
            fos.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }

}
