package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) {
        try {
            RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
            long position = Long.parseLong(args[1]);

            raf.seek(position);
            byte[] barray = new byte[args[2].length()];
            raf.read(barray, 0, args[2].length());

            String textFromFile = new String(barray, StandardCharsets.UTF_8);

            raf.seek(raf.length());
            if(textFromFile.equals(args[2])){
                raf.write("true".getBytes());
            }else{
                raf.write("false".getBytes());
            }

            raf.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
