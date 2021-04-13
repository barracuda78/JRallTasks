package com.javarush.task.task22.task2210;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.asList(getTokens("level22.lesson13.task01", ".")));

    }

    public static String[] getTokens(String query, String delimiter) {
        if (query == null) return new String[0];
        List<String> list = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        int sArrayLength = 0;
        while(tokenizer.hasMoreTokens()){
            String s = tokenizer.nextToken();
            sArrayLength++;
            list.add(s);
        }
        //String[] sArray = new String[sArrayLength];
        String[] sArray = list.toArray(new String[list.size()]);
        return sArray;
    }
}
