package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream consoleWriter = System.out;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        System.setOut(printStream);
        testString.printSomething();

        String s = byteArrayOutputStream.toString();

        List<String> list = new ArrayList<>();
        String[] sArray = s.split("\\n");
        for(int i = 0; i < sArray.length; i++){
            if(i%2!=0){
                list.add(sArray[i]);
                list.add("JavaRush - курсы Java онлайн");
            }
            else{
                list.add(sArray[i]);
            }

        }

        System.setOut(consoleWriter);
        //System.out.println(result);
        for(String string : list){
            System.out.println(string);
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
