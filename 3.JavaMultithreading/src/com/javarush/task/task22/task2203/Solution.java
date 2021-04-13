package com.javarush.task.task22.task2203;

/* 
Между табуляциями
Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
На некорректные данные бросить исключение TooShortStringException.
Класс TooShortStringException не менять.
Требования:
1. Класс TooShortStringException должен быть потомком класса Exception.
2. Метод getPartOfString должен принимать строку в качестве параметра.
3. В случае, если строка, переданная в метод getPartOfString содержит менее 2 табуляций должно возникнуть исключение TooShortStringException.
4. Метод getPartOfString должен возвращать подстроку между первой и второй табуляцией.
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException{
        if(string == null) throw new TooShortStringException();
        String s = "";


        String[] sArray = string.split("\t");

        if(sArray.length >= 3){
            char[] chArray = string.toCharArray();
            int secondTabIndex = -1; //это будет индекс пятого пробела. А так же это индекс конца слова, которое следует после 4-го пробела
            int counter = 0; // счетчик встретившихся в строке пробелов

            for (int i = 0; i < chArray.length; i++) {
                if (chArray[i] == '\t') {
                    counter++;
                    if (counter == 3) break;
                    secondTabIndex = i;
                }
            }
            s = string.substring(string.indexOf("\t")+1, secondTabIndex);
        }
        else{
            throw new TooShortStringException();
        }

        return s;
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
