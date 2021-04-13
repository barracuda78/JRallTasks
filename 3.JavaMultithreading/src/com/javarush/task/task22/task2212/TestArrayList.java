package com.javarush.task.task22.task2212;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestArrayList {



    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);


        list.add(0, 999);

        System.out.println(Arrays.asList(list));
    }
}
