package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        try {
            //читаем с консоли имена двух файлов.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String file1 = bufferedReader.readLine();
            String file2 = bufferedReader.readLine();
            bufferedReader.close();

            //читаем строки из файл1 в лист1
            List<String> list1 = new ArrayList<>();
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(file1));
            while(bufferedReader1.ready()){
                String s = bufferedReader1.readLine();
                list1.add(s);
            }
            bufferedReader1.close();

            //читаем строки из файл2 в лист2
            List<String> list2 = new ArrayList<>();
            BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file2));
            while(bufferedReader2.ready()){
                String s = bufferedReader2.readLine();
                list2.add(s);
            }
            bufferedReader2.close();

            //сравниваем один за другим элементы лист1 и лист2.
            //Если first == first тогда SAME first
            //Если first != first тогда проверяем:
                // если second == first, тогда REMOVED first. И тогда
                // если first == second, тогда ADDED first

            //создаем итератор по длине наименьшего списка.
            int iterator;
            if(list1.size() > list2.size()){
                iterator = list1.size();
            } else{
                iterator = list2.size();
            }

            for (int i = 0; ; i++) {
                if(i == list1.size() || i == list2.size()) {
                    //проверить, какой из списков длиннее.
                    //если List1 длиннее, то последняя строка лист 1 = REMOVED
                    //если list2 длиннее, то последняя строка лист2 = ADDED
                    //если листы одинаковые - то еще не решил. Возможно, ничего не делать.
                    if(list1.size() > list2.size()){
                        lines.add(new LineItem(Type.REMOVED, list1.get(list1.size()-1)));
                    }
                    else if(list1.size() < list2.size()){
                        lines.add(new LineItem(Type.ADDED, list2.get(list2.size()-1)));
                    }
                    break;
                }
                if(list1.get(i).equals(list2.get(i))){
                    lines.add(new LineItem(Type.SAME, list1.get(i)));

                }
                else if(list1.get(i+1).equals(list2.get(i))){
                    lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                    //вставить в список 2, где удален, значение "-empty-". Чтобы выровнять строки.
                    list2.add(i, "-=empty=-");
                }
                else if(list1.get(i).equals(list2.get(i+1))){
                    lines.add(new LineItem(Type.ADDED, list2.get(i)));
                    //вставить в список 1, где образовался "пробел" из-за добавления в списке2, значение "-empty-". Чтобы выровнять строки.
                    list1.add(i, "-=empty=-");
                }
            }

            //чтобы итерироваться до самого конца удлинненных списков, создадим каунтер количества удлиннений и пройдемся вторым аналогичным циклом по нему.



            //-------> check. REmove before validating.
            for(LineItem l : lines){
                System.out.println(l.toString());
            }


        }
        catch(IOException io){
            io.printStackTrace();
        }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

        //----------------------------> remove before validating
        @Override
        public String toString(){
            return type +" "+ line;
        }
    }
}
