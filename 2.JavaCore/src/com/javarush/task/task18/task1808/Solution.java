package com.javarush.task.task18.task1808;

/* 
Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать большую часть.
Закрыть потоки.

Требования:
1. Программа должна три раза считать имена файлов с консоли.
2. Для чтения из файла используй поток FileInputStream, для записи в файлы - FileOutputStream
3. Первую половину байт из первого файла нужно записать во второй файл.
4. Вторую половину байт из первого файла нужно записать в третий файл.
5. Потоки FileInputStream и FileOutputStream должны быть закрыты.
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    static String fileName1 = null;
    static String fileName2 = null;
    static String fileName3 = null;

    public static void main(String[] args) {
        readFileNames();
        fileWriter(writeBytesToArrayList());

    }
    //метод для сохранения имен файлов в ArrayList.
    public static void readFileNames(){
        ArrayList<String> listOfFileNames = new ArrayList<>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            fileName1 = bufferedReader.readLine();
            fileName2 = bufferedReader.readLine();
            fileName3 = bufferedReader.readLine();

            bufferedReader.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }

    //метод для записи байт из файла 1 в ArrayList
    public static ArrayList<Integer> writeBytesToArrayList(){
        ArrayList<Integer> list = new ArrayList<>();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName1);
            while (fis.available() > 0) {
                Integer byteInt = fis.read();
                list.add(byteInt);
            }
            fis.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }

        return list;
    }

    //метод для записи байт из ArrayList в два файла.
    public static void fileWriter(ArrayList<Integer> list){
        try {
            FileOutputStream fos2 = new FileOutputStream(fileName2);
            FileOutputStream fos3 = new FileOutputStream(fileName3);

            if(list.size()%2 == 0){
                for(int i = 0; i < list.size()/2; i++){
                    fos2.write(list.get(i));
                }
                for(int i = list.size()/2; i < list.size(); i++){
                    fos3.write(list.get(i));
                }
            }
            else{
                for(int i = 0; i < (list.size()/2)+1; i++){
                    fos2.write(list.get(i));
                }
                for(int i = (list.size()/2)+1; i < list.size(); i++){
                    fos3.write(list.get(i));
                }
            }
            fos2.close();
            fos3.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }

}
