package com.javarush.task.task19.task1925;

/* Длинные слова В метод main первым параметром приходит имя файла1, вторым - файла2.
Файл1 содержит слова, разделенные пробелом.
Записать через запятую в Файл2 слова, длина которых строго больше 6.
В конце файла2 запятой не должно быть.
Закрыть потоки.

Пример выходных данных в файл2:
длинное,короткое,аббревиатура

Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое первого файла (используй FileReader c конструктором String).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна записывать через запятую во второй файл все слова из первого файла длина которых строго больше 6(используй FileWriter).
5. Поток записи в файл (FileWriter) должен быть закрыт.*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try {

            String fileName1 = args[0];
            String fileName2 = args[1];

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName1));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2));
            StringBuilder result = new StringBuilder();
            while(bufferedReader.ready()){
                String s = bufferedReader.readLine();
                String[] sArray = s.split(" ");
                for(int i = 0; i < sArray.length; i++){
                    if(sArray[i].length() > 6){
                            result.append(sArray[i] + ",");
                    }
                }
            }
            result = result.deleteCharAt(result.length()-1);
            bufferedWriter.write(result.toString());
            bufferedReader.close();
            bufferedWriter.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
