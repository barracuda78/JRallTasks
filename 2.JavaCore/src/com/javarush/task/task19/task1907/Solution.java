package com.javarush.task.task19.task1907;

/* 
Считаем слово
Считать с консоли имя файла.
Файл содержит слова, разделенные знаками препинания.
Вывести в консоль количество слов "world", которые встречаются в файле.
Закрыть потоки.

Требования:
1. Программа должна считывать имя файла с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое файла (используй FileReader c конструктором принимающим String).
4. Поток чтения из файла (FileReader) должен быть закрыт.
5. Программа должна выводить в консоль количество слов "world", которые встречаются в файле.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        try{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = bufferedReader.readLine();
        bufferedReader.close();

        String s = "";

            FileReader fileReader = new FileReader(fileName1);
            while(fileReader.ready()){
                char b = (char)fileReader.read();
                s += b;
            }
            fileReader.close();

            String[] sArray = s.split("\\W");

            int counter = 0;
            for (int i = 0; i < sArray.length; i++) {
                if(sArray[i].equals("world")){
                    counter++;
                }
            }
            System.out.println(counter);
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
