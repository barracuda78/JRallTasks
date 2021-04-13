package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

public class Solution {
    public static void main(String... args) {
        try {
            RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
            long fileSize = raf.length();
            if(Long.parseLong(args[1]) <= fileSize){
                raf.seek(Long.parseLong(args[1]));
            }else{
                raf.seek(fileSize);
            }
            raf.write(args[2].getBytes());

            raf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
