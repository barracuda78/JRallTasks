package com.javarush.task.task20.task2010;

import java.io.*;

/*
Как сериализовать что-то свое?
1. Класс Object должен существовать внутри класса Solution.
2. Класс Solution.Object должен быть статическим.
3. Класс Solution.Object должен быть публичным.
4. Класс Solution.Object должен поддерживать интерфейс Serializable.
5. Класс Solution.String должен поддерживать интерфейс Serializable.
*/
public class Solution {
    public static class Object implements Serializable {
        public String string1;
        public String string2;
    }

    public static int stringCount;

    public static class String implements Serializable{
        private final int number;

        public String() {
            number = ++stringCount;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }

    public static void main(java.lang.String[] args) throws  Exception{
//        Solution.Object object01 = new Object();
//        object01.string1 = new String();
//        object01.string2 = new String();
//        System.out.println(object01);
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2010\\1")));
//        oos.writeObject(object01);
//
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2010\\1")));
//
//        Solution.Object object02 = (Solution.Object)ois.readObject();
//        System.out.println(object02);

    }
}
