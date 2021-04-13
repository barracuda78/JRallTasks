package com.javarush.task.task19.task1910;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestSystemOutChange  {
    public static void main(String[] args) {
        PrintStream consoleStream = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        PrintStream stream = new PrintStream(byteArrayOutputStream);

        System.setOut(stream);

        printSomething();

        String result = byteArrayOutputStream.toString();

        System.setOut(consoleStream);

        //разворачиваем строку
        StringBuilder stringBuilder = new StringBuilder(result);
        stringBuilder.reverse();
        String reverseString = stringBuilder.toString();

        //выводим ее в консоль
        System.out.println(reverseString);

    }

    public static void printSomething()
    {
        System.out.println("Hi");
        System.out.println("My name is Amigo");
        System.out.println("Bye-bye!");
    }
}
