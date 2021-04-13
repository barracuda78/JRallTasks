package com.javarush.task.task19.task1923;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\b.*\\d.*\\b");
        Matcher matcher = pattern.matcher("dsada eeeeee dsadsa");
        System.out.println(matcher.matches());
    }
}
