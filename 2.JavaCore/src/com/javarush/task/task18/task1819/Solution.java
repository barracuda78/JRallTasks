package com.javarush.task.task18.task1819;

/* Объединение файлов
Считать с консоли 2 имени файла.
В начало первого файла записать содержимое второго файла так, чтобы получилось объединение файлов.
Закрыть потоки.
Требования:
1. Программа должна два раза считать имена файлов с консоли.
2. Не используй в программе статические переменные.
3. Для первого файла создай поток для чтения и считай его содержимое.
4. Затем, для первого файла создай поток для записи(поток для записи должен быть один). Для второго - для чтения.
5. Содержимое первого и второго файла нужно объединить в первом файле.
6. Сначала должно идти содержимое второго файла, затем содержимое первого.
7. Созданные для файлов потоки должны быть закрыты.*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> listOfFileNames = readFileNames();
        ArrayList<Integer> bytesOfFirstFile = readFile(listOfFileNames.get(0));
        ArrayList<Integer> bytesOfSecondFile = readFile(listOfFileNames.get(1));
        ArrayList<Integer> unionList = unionOfLists(bytesOfSecondFile, bytesOfFirstFile);
        writeFile(listOfFileNames.get(0), unionList);
    }
    //метод для чтения имен файлов в ArrayList.
    public static ArrayList<String> readFileNames(){
        ArrayList<String> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < 2; i++) {
                list.add(bufferedReader.readLine());
            }
            bufferedReader.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
        return list;
    }

    //метод для чтения содержимого файла в ArrayList<Integer>.
    public static ArrayList<Integer> readFile(String fileName){
        ArrayList<Integer> listFirstFile = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            //int available = fileInputStream.available();
            //listFirstFile.add(available); //записали в позицию НОЛЬ листа реальное количество байт.
            while (bufferedInputStream.available() > 0){
                listFirstFile.add(bufferedInputStream.read());
            }

            bufferedInputStream.close();
            fileInputStream.close();
        }
        catch (IOException i){
            i.printStackTrace();
        }
        return listFirstFile;
    }

    //метод для объединения ArrayList'ов.
    public static ArrayList<Integer> unionOfLists(ArrayList<Integer> list1, ArrayList<Integer> list2){

        ArrayList<Integer> unionList = new ArrayList<Integer>(list1);
        unionList.addAll(list2);

//        ArrayList<Integer> unionList = new ArrayList<>();
//        for(int i = 0; i < list1.size(); i++){
//            unionList.add(list1.get(i));
//        }
//        for(int i = list1.size(); i < (list1.size() + list2.size()); i++){
//            unionList.add(list2.get(i));
//        }
        return unionList;
    }

    //метод для записи в файл
    public static void writeFile(String fileName, ArrayList<Integer> list1){
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName, false));
            for (int i = 0; i < list1.size(); i++) {
                bufferedOutputStream.write(list1.get(i));
            }

            bufferedOutputStream.close();
        }
        catch (IOException i){
            i.printStackTrace();
        }
    }

}
