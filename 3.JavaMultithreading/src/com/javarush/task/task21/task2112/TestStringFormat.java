package com.javarush.task.task21.task2112;

import java.text.Format;
import java.util.Formatter;

public class TestStringFormat {
    public static void main(String[] args) {
        double distance = 3.3;
        String name = "Kaurka";

        System.out.println(String.format("%" + (long)Math.floor(distance) + "s" + name, "").replaceAll(" ", "."));

    }
}
