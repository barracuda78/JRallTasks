package com.javarush.task.task19.task1909;

/* Замена знаков
Считать с консоли 2 имени файла.
Первый Файл содержит текст.
Считать содержимое первого файла и заменить все точки "." на знак "!".
Результат вывести во второй файл.
Закрыть потоки.
Требования:
1. Программа должна считывать имена файлов с консоли (используй BufferedReader).
2. BufferedReader для считывания данных с консоли должен быть закрыт.
3. Программа должна считывать содержимое первого файла (используй BufferedReader c конструктором FileReader).
4. Поток чтения из файла (BufferedReader) должен быть закрыт.
5. Программа должна записывать во второй файл содержимое первого файла, где заменены все точки "." на знак "!" (Для записи в файл используй BufferedWriter с конструктором FileWriter).
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
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2));
            while(br.ready()){
                char ch = (char)br.read();
                if (ch == '.') ch = '!';
                bufferedWriter.write(ch);
            }
            br.close();
            bufferedWriter.close();

        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
