package com.javarush.task.task19.task1920;

/* Самый богатый   В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Вывести в консоль имена в алфавитном порядке, у которых максимальная сумма.
Имена разделять пробелом либо выводить с новой строки.
Закрыть потоки.

Пример входного файла:
Петров 0.501
Иванов 1.35
Петров 0.85

Пример вывода:
Петров

Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое файла (используй FileReader).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна выводить в консоль имена, у которых максимальная сумма.*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        try {
            //читаем имя файла
            String fileName = args[0];
            //создаем мапу для всех ключей и значений
            Map<String, Double> map = new HashMap<>();

            //Читаем строки из файла, парсим и добавляем ключи-значения в мапу
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while(bufferedReader.ready()){
                String s = bufferedReader.readLine();
                String name = s.substring(0, s.indexOf(" "));
                Double salary = Double.parseDouble(s.substring(s.indexOf(" ") + 1));
                if(map.containsKey(name)){
                    salary += map.get(name);
                    map.put(name, salary);

                }
                else{
                    map.put(name, salary);

                }
            }
            bufferedReader.close();

            //создаем список именсамых богатых.
            List<String> listOfRichest = new ArrayList<>();
            //найдем максимальную величину мапы.
            Double max = Double.MIN_VALUE;
            // для этого итерируемся по мапе. Сравниваем значения с max.
            for(Map.Entry<String, Double> pair : map.entrySet()){
                String key = pair.getKey();
                Double value = pair.getValue();

                if(value > max) max = value;
            }
            //добавим все имена с максимальным доходом в listOdRichest
            for(Map.Entry<String, Double> pair : map.entrySet()){
                String key = pair.getKey();
                Double value = pair.getValue();

                if(value.equals(max)) listOfRichest.add(key);
            }

            //сортируем по алф порядку список богачей.
            Collections.sort(listOfRichest);


            //выводим богачей в консоль через пробел.
            for(String name : listOfRichest){
                System.out.println(name + " ");
            }

        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
