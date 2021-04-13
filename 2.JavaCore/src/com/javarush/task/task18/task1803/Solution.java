package com.javarush.task.task18.task1803;

/* 
Самые частые байты
Ввести с консоли имя файла.
Найти байт или байты с максимальным количеством повторов.
Вывести их на экран через пробел.
Закрыть поток ввода-вывода.

Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль через пробел должны выводиться все байты из файла с максимальным количеством повторов.
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
        mapPrinter(mapPlacer(writeBytesToArrayList(fileNameReader())));
//        char myChar = 50;
//        char myChar2 = 49;
//       System.out.println(myChar);
//       System.out.println(myChar2);
    }
//метод для чтения имени файла из консоли
    private static String fileNameReader(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = null;
        try {
            fileName = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

//метод для записи байтов из файла в ArrayList
    private static ArrayList<Integer> writeBytesToArrayList(String filename) throws IOException {
        ArrayList<Integer> list = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filename);
        while(fis.available() > 0){
            list.add(fis.read());
        }
        fis.close();
        return list;
    }

//метод для помещения байтов и их частот в мапу.
    private static Map<Integer, Integer> mapPlacer(ArrayList<Integer> list){
        Map<Integer, Integer> map = new HashMap<>();
        Collections.sort(list);
        for(Integer byteInt : list){
            int temp = Collections.frequency(list, byteInt);
            map.put(byteInt, temp);
        }
        return map;
    }
//метод для проверки того, что в мапе
    private static void mapPrinter(Map<Integer, Integer> map){
        int max = 0;
        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            int value = pair.getValue();
            //System.out.println(key + " : " + value);
            if(value >= max){
                max = value;
            }
        }
        for(Map.Entry<Integer, Integer> pair : map.entrySet()) {
            int key = pair.getKey();
            int value = pair.getValue();

            if(value == max){
                System.out.print(key + " ");
            }
        }

    }
}


