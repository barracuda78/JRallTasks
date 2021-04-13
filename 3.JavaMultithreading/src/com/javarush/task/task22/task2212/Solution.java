package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
+380501234567 - true
+38(050)1234567 - true
+38050123-45-67 - true
050123-4567 - true
+38)050(1234567 - false
+38(050)1-23-45-6-7 - false
050ххх4567 - false
050123456 - false
(0)501234567 - false
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        boolean matches = false;


        if (telNumber == null|| telNumber.equals("")) return false;


        matches = Pattern.matches("^(\\+\\d{2})?\\(?\\d{3}\\)?\\d{3}-?\\d{2}-?\\d{2}$", telNumber);

        return matches;
    }

    public static void main(String[] args) {

        System.out.println(checkTelNumber("+38(0501234567"));
    }
}
