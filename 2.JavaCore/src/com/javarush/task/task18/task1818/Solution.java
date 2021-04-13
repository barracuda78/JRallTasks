package com.javarush.task.task18.task1818;

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> list = readFileNames();
        writeSecondToFirst(list.get(0), list.get(1));
        writeThirdToFirst(list.get(0), list.get(2));
    }
    //метод для чтения имен файлов в ArrayList.
    public static ArrayList<String> readFileNames(){
        ArrayList<String> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < 3; i++) {
                list.add(bufferedReader.readLine());
            }
            bufferedReader.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
        return list;
    }

    //метод для записи в первый файл содержимого второго файла.
    public static void writeSecondToFirst(String fileName01, String fileName02){
        try {
            FileInputStream fis = new FileInputStream(fileName02);
            FileOutputStream fos = new FileOutputStream(fileName01);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            while(bis.available() > 0){
                bos.write(bis.read());
            }
            bis.close();
            fis.close();
            bos.close();
            bis.close();
        }
        catch (IOException i){
            i.printStackTrace();
        }
    }

    //метод для дозаписи третьего файла в первый файл.
    public static void writeThirdToFirst(String fileName01, String fileName03){
        try {
            FileInputStream fis = new FileInputStream(fileName03);
            FileOutputStream fos = new FileOutputStream(fileName01, true);
            BufferedInputStream bis = new BufferedInputStream(fis);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            while(bis.available() > 0){
                bos.write(bis.read());
            }
            bis.close();
            fis.close();
            bos.close();
            bis.close();
        }
        catch (IOException i){
            i.printStackTrace();
        }
    }
}
