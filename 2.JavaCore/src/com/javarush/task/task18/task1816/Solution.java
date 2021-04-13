package com.javarush.task.task18.task1816;

/* Английские буквы
В метод main первым параметром приходит имя файла.
Посчитать количество букв английского алфавита, которое есть в этом файле.
Вывести на экран число (количество букв).
Закрыть потоки.
Требования:
1. Считывать с консоли ничего не нужно.
2. Создай поток чтения из файла, который приходит первым параметром в main.
3. В файле необходимо посчитать буквы английского алфавита и вывести это число в консоль.
4. Нужно учитывать заглавные и строчные буквы.
5. Поток чтения из файла должен быть закрыт.*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {

    public static void main(String[] args) throws IOException {

        //String fileName = "C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1816\\test.txt";
        String fileName = args[0];
        int counter = 0;
        int[] alphabet = new int[26+26];
        for (int i = 0; i < 26; i++) {
            alphabet[i] = 65 + i;
        }
        for (int i = 26; i < 26+26; i++) {
            alphabet[i] = 97 + i - 26;
        }

        FileInputStream fis = new FileInputStream(fileName);
        while(fis.available() > 0){
            int byteInt = fis.read();
            for(int i = 0; i < alphabet.length; i++) {
                if (byteInt == alphabet[i]) counter++;
            }

        }
        fis.close();
        System.out.println(counter);
    }
}
