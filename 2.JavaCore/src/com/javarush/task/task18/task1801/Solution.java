package com.javarush.task.task18.task1801;

/* 
Максимальный байт
Ввести с консоли имя файла.
Найти максимальный байт в файле, вывести его на экран.
Закрыть поток ввода-вывода.
Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль должен выводиться максимальный байт, считанный из файла.
4. Поток чтения из файла должен быть закрыт.
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        System.out.println(readByte(readFileName()));
    }

    private static  String readFileName() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        return fileName;
    }

    private static int readByte(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        int maximumByte = 0;
        while(fis.available() > 0){
            int byteRead = fis.read();
            if(byteRead > maximumByte){
                maximumByte = byteRead;
            }
        }
        fis.close();
        return maximumByte;
    }

}
