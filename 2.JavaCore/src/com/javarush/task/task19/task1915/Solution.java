package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = bufferedReader.readLine();
            bufferedReader.close();

            PrintStream consoleWriter = System.out;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(byteArrayOutputStream);
            System.setOut(printStream);
            testString.printSomething();

            String string = byteArrayOutputStream.toString();

            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(byteArrayOutputStream.toByteArray());
            fos.close();



            System.setOut(consoleWriter);

            System.out.println(string);
        }
        catch(IOException i){
            i.printStackTrace();
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

