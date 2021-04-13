package com.javarush.task.task19.task1923;

/* Слова с цифрами  В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит строки со словами, разделенными пробелом.
Записать через пробел в Файл2 все слова, которые содержат цифры, например, а1 или abc3d.
Закрыть потоки.

Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое первого файла (используй FileReader c конструктором String).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна записывать во второй файл все слова из первого файла которые содержат цифры (используй FileWriter).
5. Поток записи в файл (FileWriter) должен быть закрыт.*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        try {
            //читаем имена файлов
            String fileName1 = args[0];
            String fileName2 = args[1];


            //создадим список для хранения всех слов с цифрами.
            List<String> list = new ArrayList<>();
            //читаем содержимое первого файла
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName1));

            while(bufferedReader.ready()){
                //прочитали одну строку из файла.
                String s = bufferedReader.readLine();
                //запишем в массив все слова этой одной строки файла.
                String[] sArray = s.split(" ");
                //проитерируемся по массиву и проверим каждое слово в массиве на наличие цифр.
                for (int i = 0; i < sArray.length; i++) {
                    //разобъем каждое слово строки на массив char
                    char[] charArray = sArray[i].toCharArray();
                    //во внутреннем цикле итерируемся по массиву char и ищем совпадение с символами 0-9
                    for (int j = 0; j < charArray.length; j++) {
                        if (charArray[j] >= 48 & charArray[j] <= 57){
                            list.add(sArray[i]);
                            break;
                        }
                    }
                }
            }
            bufferedReader.close();

            //запишем слова из списка в файл через пробел:
            FileWriter fileWriter = new FileWriter(fileName2);
            for(String string : list){
                fileWriter.write(string + " ");
            }
            fileWriter.close();

        }
        catch(IOException i){
            i.printStackTrace();
        }

    }
}
