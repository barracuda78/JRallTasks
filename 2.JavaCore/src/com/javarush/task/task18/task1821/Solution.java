package com.javarush.task.task18.task1821;

/* Встречаемость символов
Посчитать частоту встречания каждого символа.
Отсортировать результат по возрастанию кода ASCII (почитать в инете).

Пример:
','=44, 's'=115, 't'=116.

Вывести на консоль отсортированный результат:
[символ1] частота1
[символ2] частота2
Закрыть потоки.

Пример вывода:
, 19
- 7
f 361

Требования:
1. Считывать с консоли ничего не нужно.
2. Создай поток для чтения из файла, который приходит первым параметром в main.
3. В файле необходимо посчитать частоту встречания каждого символа и вывести результат.
4. Выведенный в консоль результат должен быть отсортирован по возрастанию кода ASCII.
5. Поток для чтения из файла должен быть закрыт.*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        String fileName = args[0];   //----------------------> РАЗКОММЕНТИТЬ
        //String fileName = "C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task18\\task1821\\file01.txt";
        Map<Character, Integer> map = new HashMap<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            while(fileInputStream.available() > 0){
                char ch = (char)fileInputStream.read();

                if(map.containsKey(ch)) {
                    Integer value = map.get(ch);
                    map.put(ch, value+1);
                }
                else{
                    map.put(ch, 1);
                }
            }
            fileInputStream.close();

            ArrayList<Character> listOfChars = new ArrayList<>();
            for(Map.Entry<Character, Integer> pair : map.entrySet()){
                Character key = pair.getKey();
                Integer value = pair.getValue();
                listOfChars.add(key);
            }

            Collections.sort(listOfChars);

            for(Character chars : listOfChars){
                System.out.print(chars);
                System.out.print(" ");
                for(Map.Entry<Character, Integer> pair : map.entrySet()){
                    Character key = pair.getKey();
                    Integer value = pair.getValue();
                    if(chars == key){
                        System.out.print(value);
                        System.out.println();
                    }
                }

            }



        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
