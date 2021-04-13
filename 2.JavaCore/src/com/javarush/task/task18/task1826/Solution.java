package com.javarush.task.task18.task1826;

import java.io.*;
public class Solution {

    public static void main(String[] args) throws IOException {

        System.out.println((int)Character.MIN_VALUE);
        System.out.print((int)Character.MAX_VALUE);
//           String parameter = args[0];               //------------------------------------------> РАЗКОММЕНТИТЬ
//           String fileFromName = args[1];
//           String fileToName = args[2];

           String parameter = "-d";
           String fileFromName = "C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1826\\2.txt";
           String fileToName = "C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1826\\1.txt";

           if (parameter.equals("-e")) {
               cipher(fileFromName, fileToName);
           } else if (parameter.equals("-d")) {
               decipher(fileFromName, fileToName);
           } else {
               System.out.println("неправильный параметр");
           }
    }

    //метод для шифрования. Читаем байт. Сравниваем с ключом мапы, записываем увеличенный на единицу байт в другой файл.
    private static void cipher(String fileFromName, String fileToName) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileFromName));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileToName));

        while(bufferedInputStream.available() > 0){
            int byteInt = bufferedInputStream.read();
            if(byteInt == 65535) bufferedOutputStream.write(0);
            bufferedOutputStream.write(byteInt + 1);
        }

        bufferedInputStream.close();
        bufferedOutputStream.close();

    }

    //метод для дешифрования. Читаем байт. Сравниваем со значением. Записываем в другой файл уменьшенный на единицу "ключ" из мапы.
    private static void decipher(String fileFromName, String fileToName) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(fileFromName));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileToName));

        while(bufferedInputStream.available() > 0){
            int byteInt = bufferedInputStream.read();
            if(byteInt == 0) bufferedOutputStream.write(65535);
            bufferedOutputStream.write(byteInt - 1);
        }

        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}

