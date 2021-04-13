package com.javarush.task.task26.task2603;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Убежденному убеждать других не трудно
*/

class FirstComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        if(o1.length() > o2.length())
            return 1;
        else if(o1.length() < o2.length())
            return -1;
        else
            return 0;
    }
}

class SecondComparator implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        if(o1.charAt(0) > o2.charAt(0))
            return 1;
        else if(o1.charAt(0) < o2.charAt(0))
            return -1;
        else
            return 0;
    }
}
public class Solution {

    public static void main(String[] args) {
        CustomizedComparator<String> cc = new CustomizedComparator<>(new FirstComparator(), new SecondComparator());
        List<String> list = new ArrayList<>();
        list.add("Ola");
        list.add("Andrey");
        list.add("Lida");
        list.add("Clown");
        list.add("Boba");

        Collections.sort(list, cc);

        System.out.println(list);
    }

    public static class CustomizedComparator<T> implements Comparator<T> {

        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>...t){
            comparators = t;
        }

        int result = 0;
        @Override
        public int compare(T o1, T o2) {
            for(Comparator<T> comparator : comparators){
                result = comparator.compare(o1, o2);

                if(result != 0){
                    break;
                }
            }
            return result;
        }
    }
}
