package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Замена чисел
*/

public class Test {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static{
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        try {
            //читаем с консоли имя файла
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = bufferedReader.readLine();
            bufferedReader.close();

            //читаем данные из файла
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(fileName));
            while(bufferedReader1.ready()){
                String s = bufferedReader1.readLine();

                String[] sArray = s.split(" ");
                for (int i = 0; i < sArray.length; i++) {
                    Pattern p = Pattern.compile("[0-9][0-2]?");
                    Matcher m = p.matcher(sArray[i]);

                    if(m.matches()) {
                        int number = Integer.parseInt(sArray[i]);
                        sArray[i] = map.get(number);
                    }

                }
                //собрать строку из массива sArray.
                String sNew = "";
                for(int i = 0; i < sArray.length; i++){
                    if(i == sArray.length - 1) {
                        sNew += sArray[i];
                    }
                    else {
                        sNew += sArray[i] + " ";
                    }
                }


                System.out.println(sNew);


                //Это стоит 1 бакс, а вот это - 12 .
                //Переменная имеет имя file1.
                //110 - это число.
            }
            bufferedReader1.close();

        }catch(IOException i){
            i.printStackTrace();
        }


    }
}
