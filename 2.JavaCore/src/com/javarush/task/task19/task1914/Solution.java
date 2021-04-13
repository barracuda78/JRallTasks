package com.javarush.task.task19.task1914;
/* Решаем пример В методе main подмени объект System.out написанной тобой ридер-оберткой по аналогии с лекцией.
Твоя ридер-обертка должна выводить на консоль решенный пример.
Вызови готовый метод printSomething(), воспользуйтесь testString.
Верни переменной System.out первоначальный поток.

Возможные операции: + - *
Шаблон входных данных и вывода: a [знак] b = c
Отрицательных и дробных чисел, унарных операторов - нет.

Пример вывода:
3 + 6 = 9

Требования:
1. Класс Solution должен содержать класс TestString.
2. Класс Solution должен содержать публичное статическое поле testString типа TestString, которое сразу проинициализировано.
3. Класс TestString должен содержать публичный void метод printSomething().
4. Метод printSomething() класса TestString должен выводить на экран строку "3 + 6 = ".
5. Метод main(String[] args) класса Solution должен создавать поток PrintStream (используй PrintStream c параметром конструктора ByteArrayOutputStream).
6. Метод main(String[] args) класса Solution должен подменять и восстанавливать поток вывода в консоль объекта System.out.
7. Метод main(String[] args) класса Solution должен вызывать метод printSomething(),объекта testString.
8. Метод main(String[] args) класса Solution должен модифицировать строку выведенную методом printSomething() согласно заданию, и выводить её в консоль.*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream consoleWriter = System.out;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);

        System.setOut(printStream);
        testString.printSomething();
        String string = byteArrayOutputStream.toString();

        int number01 = Integer.parseInt(string.substring(0, string.indexOf(" ")));
        int number02 = Integer.parseInt(string.substring(string.indexOf(" ") + 3, string.indexOf("=") - 1));



        System.setOut(consoleWriter);

        if(string.substring(string.indexOf(" ") + 1, string.indexOf(" ") + 2).equals("+")){
            System.out.println(number01 + " + " + number02 + " = " + (number01+number02));
        }
        else if(string.substring(string.indexOf(" ") + 1, string.indexOf(" ") + 2).equals("-")){
            System.out.println(number01 + " - " + number02 + " = " + (number01-number02));
        }
        else if(string.substring(string.indexOf(" ") + 1, string.indexOf(" ") + 2).equals("*")){
            System.out.println(number01 + " * " + number02 + " = " + (number01*number02));
        }
        else{

        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

