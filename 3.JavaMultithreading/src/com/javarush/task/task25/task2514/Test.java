package com.javarush.task.task25.task2514;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test{
    public static void main(String[] args) throws Exception{
        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");

        System.out.println(list);
        list = Collections.unmodifiableList(list);
        //list.remove(1);
        System.out.println("list after removing 1st element: " + list);
    }
}