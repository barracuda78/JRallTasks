package com.javarush.task.task19.task1908;

/* 
Выделяем числа
Считать с консоли 2 имени файла.
Вывести во второй файл все числа, которые есть в первом файле.
Числа выводить через пробел.
Закрыть потоки.

Пример тела файла:
12 text var2 14 8ю 1

Результат:
12 14 1

Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй BufferedReader c конструктором принимающим FileReader).
4. Поток чтения из файла (BufferedReader) должен быть закрыт.
5. Программа должна записывать во второй файл все числа, через пробел, из первого файла (используй BufferedWriter с конструктором FileWriter).
6. Поток записи в файл (BufferedWriter) должен быть закрыт.
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {

        try {
            //читаем имена файлов с консоли
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName1 = bufferedReader.readLine();
            String fileName2 = bufferedReader.readLine();
            bufferedReader.close();

            //читаем байты из первого файла в список
            List<String> list = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(fileName1));
            while(br.ready()){
                String s = br.readLine();
                list.add(s);
            }
            br.close();


            List<String> listInteger = new ArrayList<>();
            for (int i = 0 ; i < list.size(); i++){
                String[] sArray = list.get(i).split(" ");
                for (int j = 0; j < sArray.length; j++) {
                    //если строка из массива sArray содержит только цифры, записываем ее в список listInteger
                    String s = sArray[j];
                    if(s.matches("[0-9]+")) {
                        listInteger.add(sArray[j]);
                    }
                    else{

                    }
                }
            }

            //из второго листа пишем числа во второй файл через пробел.
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2));
            for (int i = 0; i < listInteger.size(); i++) {
                bufferedWriter.write(listInteger.get(i) + " ");
            }
            bufferedWriter.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
