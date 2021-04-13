package com.javarush.task.task22.task2202;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

public class TestRegEx {
    public static void main(String[] args) {
        String regex = "\\S* \\S* \\S* \\S* \\S* \\S*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher("JavaRush - лучший сервис обучения Java.");
        boolean b = m.matches();
        System.out.println(b);
    }
}
