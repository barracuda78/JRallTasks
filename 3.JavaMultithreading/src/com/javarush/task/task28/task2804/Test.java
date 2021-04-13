package com.javarush.task.task28.task2804;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();

        while(iterator.hasNext()){
            Map.Entry<Integer, String> entry = iterator.next();
            Integer key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }

    }
}
