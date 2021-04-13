package com.javarush.task.task18.task1804;

/* 
Самые редкие байты
Ввести с консоли имя файла.
Найти байт или байты с минимальным количеством повторов.
Вывести их на экран через пробел.
Закрыть поток ввода-вывода.
Требования:
1. Программа должна считывать имя файла с консоли.
2. Для чтения из файла используй поток FileInputStream.
3. В консоль через пробел должны выводиться все байты из файла с минимальным количеством повторов.
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
        mapCheck(mapPlacer(writeBytesToArrayList(readFileName())));
    }

    //метод для чтения имени файла из консоли
    public static String readFileName() {
        String fileName = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            fileName = bufferedReader.readLine();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    //метод для записи байтов из файла в ArrayList
    public static ArrayList<Integer> writeBytesToArrayList(String fileName) {
        ArrayList<Integer> list = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            while (fis.available() > 0) {
                list.add(fis.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return list;
        }
    }

    //метод для записи байтов и их частот в мапу.
    public static Map<Integer, Integer> mapPlacer (ArrayList < Integer > list) {
            Map<Integer, Integer> map = new HashMap<>();
            Collections.sort(list);
            for (Integer intByte : list) {
                Integer temp = Collections.frequency(list, intByte);
                map.put(intByte, temp);
            }
            return map;
        }

    //метод для проверки мапы на минимум
    public static void mapCheck (Map < Integer, Integer > map){
            int minimum = 2147483647;
            for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
                Integer key = pair.getKey();
                Integer value = pair.getValue();
                if (value <= minimum) {
                    minimum = value;
                }
            }

            for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
                Integer key = pair.getKey();
                Integer value = pair.getValue();

                if (value == minimum) {
                    System.out.print(key + " ");
                }
            }
        }
}

