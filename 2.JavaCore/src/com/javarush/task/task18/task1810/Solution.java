package com.javarush.task.task18.task1810;
/* DownloadException
1 Считывать с консоли имена файлов.
2 Если файл меньше 1000 байт, то:
2.1 Закрыть потоки работы с файлами.
2.2 Выбросить исключение DownloadException.
Требования:
1. Программа должна считать имена файлов с консоли.
2. Для чтения из файлов используй поток FileInputStream.
3. Программа должна работать, пока введенный файл не окажется меньше 1000 байт.
4. Программа должна завершиться исключением DownloadException.
5. Поток FileInputStream должен быть закрыт.*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class Solution {
    public static void main(String[] args) throws DownloadException {
        readFileNames();
    }

    public static class DownloadException extends Exception {

    }

    public static void readFileNames() throws DownloadException{

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fis = null;
        try {
            while (true) {
                String fileName = bufferedReader.readLine();
                fis = new FileInputStream(fileName);
                if(fis.available() < 1000){
                    break;
                }
            }
            bufferedReader.close();
            fis.close();
            throw new DownloadException();
        }
        catch(IOException i){
            i.printStackTrace();
        }


    }
}
