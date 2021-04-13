package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
Отслеживаем изменения
*/

public class Test {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String fileName1 = bufferedReader.readLine();
//        String fileName2 = bufferedReader.readLine();
        bufferedReader.close();
        String fileName1 = "C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1916\\file1";
        String fileName2 = "C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1916\\file2";

        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        FileReader fileReader1 = new FileReader(fileName1);
        bufferedReader = new BufferedReader(fileReader1);
        list1 = bufferedReader.lines().collect(Collectors.toList());
        bufferedReader.close();
        fileReader1.close();
        FileReader fileReader2 = new FileReader(fileName2);
        bufferedReader = new BufferedReader(fileReader2);
        list2 = bufferedReader.lines().collect(Collectors.toList());
        bufferedReader.close();
        fileReader2.close();

        int len = Math.min(list1.size(), list2.size());
        int i = 0;
        while (i < len) {
            if (list1.get(i).equals(list2.get(i))) {
                lines.add(new LineItem(Type.SAME, list1.get(i)));
            }
            if (((list1.size() > list2.size()) || (i < len - 1)) && (list1.get(i + 1).equals(list2.get(i)))) {
                lines.add(new LineItem(Type.REMOVED, list1.get(i)));
                list2.add(i, "");
            }
            if (((list1.size() < list2.size()) || (i < len - 1)) && (list1.get(i).equals(list2.get(i + 1)))) {
                lines.add(new LineItem(Type.ADDED, list2.get(i)));
                list1.add(i, "");
            }
            i++;
            len = Math.min(list1.size(), list2.size());
        }
        if (list1.size() > list2.size()) {
            lines.add(new LineItem(Type.REMOVED, list1.get(list1.size() - 1)));
        }
        if (list1.size() < list2.size()) {
            lines.add(new LineItem(Type.ADDED, list2.get(list2.size() - 1)));
        }
//        for (LineItem li : lines) {
//            System.out.println(li.type + " " + li.line);
//        }

        for(Test.LineItem l : lines){
            System.out.println(l.toString());
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
        @Override
        public String toString(){
            return type +" "+ line;
        }
    }
}