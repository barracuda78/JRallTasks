package com.javarush.task.task19.task1910;

/* Пунктуация  Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Считать содержимое первого файла, удалить все знаки пунктуации, включая символы новой строки.
Результат вывести во второй файл.
Закрыть потоки.
Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй BufferedReader c конструктором FileReader).
4. Поток чтения из файла (BufferedReader) должен быть закрыт.
5. Программа должна записывать во второй файл содержимое первого файла, где удалены все знаки пунктуации, включая символы новой строки (Для записи в файл используй BufferedWriter с конструктором FileWriter).
6. Поток записи в файл (BufferedWriter) должен быть закрыт.*/

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

            BufferedReader br = new BufferedReader(new FileReader(fileName1));
            List<String> list = new ArrayList<>();
            while(br.ready()){
                String s = br.readLine();
                s = s.replaceAll("\\p{Punct}", "");
                list.add(s);
            }
            br.close();

            //из листа пишем числа во второй файл через пробел.
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2));
            for (int i = 0; i < list.size(); i++) {
                bufferedWriter.write(list.get(i));
            }
            bufferedWriter.close();


        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
