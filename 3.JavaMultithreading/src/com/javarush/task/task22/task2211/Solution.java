package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.SortedMap;

/* 
Смена кодировки
В метод main первым параметром приходит имя файла, тело которого в кодировке Windows-1251.
В метод main вторым параметром приходит имя файла, в который необходимо записать содержимое первого файла в кодировке UTF-8.
Требования:
1. Программа НЕ должна считывать данные с клавиатуры.
2. Программа НЕ должна выводить данные на экран.
3. Программа должна записывать данные в файл.
4. Содержимое второго файла должно соответствовать содержимому первого файла за исключением кодировки(UTF-8).
*/
public class Solution {
    public static void main(String[] args) throws IOException {



//        String fileName1 = "C:\\coding\\javarushtasks\\JavaRushTasks\\3.JavaMultithreading\\src\\com\\javarush\\task\\task22\\task2211\\1";
//        String fileName2 = "C:\\coding\\javarushtasks\\JavaRushTasks\\3.JavaMultithreading\\src\\com\\javarush\\task\\task22\\task2211\\2";

        String fileName1 = args[0];
        String fileName2 = args[1];

        Charset Windows_1251 = Charset.forName("Windows-1251");
        Charset UTF_8 = Charset.forName("UTF-8");

        FileInputStream fis = new FileInputStream(fileName1);
        FileOutputStream fos = new FileOutputStream(fileName2);

        int bytesCount = fis.available(); //считаем кол-во байтов в файле)


        byte[] buffer = new byte[bytesCount-1];

        fis.read(buffer);
        String s = new String(buffer, Windows_1251);
        buffer = s.getBytes(UTF_8);

        fos.write(buffer);


    }
}
