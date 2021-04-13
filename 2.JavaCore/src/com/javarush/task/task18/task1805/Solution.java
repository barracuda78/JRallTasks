package com.javarush.task.task18.task1805;

/*
Сортировка байт
Ввести с консоли имя файла.
Считать все байты из файла.
Не учитывая повторений - отсортировать их по байт-коду в возрастающем порядке.
Вывести на экран.
Закрыть поток ввода-вывода.

Пример байт входного файла:
44 83 44

Пример вывода:
44 83


Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль через пробел должны выводиться все уникальные байты из файла в порядке возрастания.
4. Данные в консоль должны выводится в одну строку.
5. Поток чтения из файла должен быть закрыт.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        sortBytes(sortBytes(putToMap(putBytesToArrayList(fileNameReader()))));
    }

    //метод для чтения имени файла с консоли.
    public static String fileNameReader(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        try {
            fileName = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    //метод для чтения байтов из файла в ArrayList.
    public static ArrayList<Integer> putBytesToArrayList(String fileName){
        ArrayList<Integer> list = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            while(fis.available() > 0){
                list.add(fis.read());

            }
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException i){
            i.printStackTrace();
        }
        return list;
    }

    //метод для помещения элементов листа в мапу.
    public static Map<Integer, Integer> putToMap(ArrayList<Integer> list){
        Map<Integer, Integer> map = new HashMap<>();
        Collections.sort(list);
        for(Integer byteInt : list){
           int freq =  Collections.frequency(list, byteInt);
            map.put(byteInt, freq);
        }
        return map;
    }

    //метод для сортировки по возрастанию по байткоду без повторений.
    public static ArrayList<Integer> sortBytes(Map<Integer, Integer> map){
        ArrayList<Integer> list2 = new ArrayList<>();
        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            list2.add(pair.getKey());

        }
        return list2;
    }

    //метод для сортировки.
    public static void sortBytes(ArrayList<Integer> list2){
        Collections.sort(list2);
        for(Integer intBytes : list2){
            System.out.print(intBytes + " ");
        }
    }



}
