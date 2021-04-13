package com.javarush.task.task19.task1919;

/* Считаем зарплаты
В метод main первым параметром приходит имя файла.
В этом файле каждая строка имеет следующий вид:
имя значение
где [имя] - String, [значение] - double. [имя] и [значение] разделены пробелом.

Для каждого имени посчитать сумму всех его значений.
Все данные вывести в консоль, предварительно отсортировав в возрастающем порядке по имени.
Закрыть потоки.

Пример входного файла:
Петров 2
Сидоров 6
Иванов 1.35
Петров 3.1

Пример вывода:
Иванов 1.35
Петров 5.1
Сидоров 6.0


Требования:
1. Программа НЕ должна считывать данные с консоли.
2. Программа должна считывать содержимое файла (используй FileReader).
3. Поток чтения из файла (FileReader) должен быть закрыт.
4. Программа должна выводить в консоль каждое имя и сумму всех его значений, все данные должны быть отсортированы в возрастающем порядке по имени.
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Solution {
    public static void main(String[] args) {
        try {
            String fileName = args[0];
            Map<String, Double> map = new HashMap<>();
            List<String> list = new ArrayList<>();
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
                    list.add(name);
                }
            }
            bufferedReader.close();

            Collections.sort(list);


            for(String name : list){
                for(Map.Entry<String, Double> pair : map.entrySet()){
                    String key = pair.getKey();
                    Double value = pair.getValue();
                    if(name.equals(key)) System.out.println(key + " " + value);
                }
            }
        }
        catch(IOException i){
            i.printStackTrace();
        }
    }
}
