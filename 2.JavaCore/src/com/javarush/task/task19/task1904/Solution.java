package com.javarush.task.task19.task1904;

/* И еще один адаптер
Адаптировать Scanner к PersonScanner.
Классом-адаптером является PersonScannerAdapter.
В классе адаптере создать приватное финальное поле Scanner fileScanner. Поле инициализировать в конструкторе с одним аргументом типа Scanner.

Данные в файле хранятся в следующем виде:
Иванов Иван Иванович 31 12 1950
Петров Петр Петрович 31 12 1957

В файле хранится большое количество людей, данные одного человека находятся в одной строке. Метод read() должен читать данные только одного человека.

Требования:
1. PersonScanner должен быть интерфейсом.
2. Класс PersonScannerAdapter должен реализовывать интерфейс PersonScanner.
3. Класс PersonScannerAdapter должен содержать приватное поле fileScanner типа Scanner.
4. Класс PersonScannerAdapter должен содержать конструктор с параметром Scanner.
5. Метод close() класса PersonScannerAdapter должен делегировать полномочие такому же методу fileScanner.
6. Метод read() класса PersonScannerAdapter должен читать строку с файла, парсить её, и возвращать данные только одного человека, в виде объекта класса Person.*/

import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
//        String line = "Иванов Иван Иванович 31 12 1950";
//        String[] lineArray = line.split(" ");
//
//        String lastName = lineArray[0];
//        String firstName = lineArray[1];
//        String middleName = lineArray[2];
//        int day = Integer.parseInt(lineArray[3]);
//        int month = Integer.parseInt(lineArray[4]);
//        month = month - 1;
//        int year = Integer.parseInt(lineArray[5]);
//        year = year - 1900;
//        Date birthDate = new Date(year, month, day);
//
//        Person person = new Person(firstName, middleName, lastName, birthDate);
//        System.out.println(person.toString());
    }

    public static class PersonScannerAdapter implements PersonScanner{
        private final Scanner fileScanner;
        public PersonScannerAdapter(Scanner fileScanner){
            this.fileScanner = fileScanner;
        }

//6. Метод read() класса PersonScannerAdapter должен читать строку с файла, парсить её, и возвращать данные только одного человека, в виде объекта класса Person.


        @Override
        public Person read() throws IOException {

            String line = fileScanner.nextLine();
            String[] lineArray = line.split(" ");
//Иванов Иван Иванович 31 12 1950
            String lastName = lineArray[0];
            String firstName = lineArray[1];
            String middleName = lineArray[2];

            int day = Integer.parseInt(lineArray[3]);
            int month = Integer.parseInt(lineArray[4]);
                month = month - 1;

            int year = Integer.parseInt(lineArray[5]);
            year = year - 1900;

            Date birthDate = new Date(year, month, day);

            return new Person(firstName, middleName, lastName, birthDate);

        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
