package com.javarush.task.task20.task2009;

import java.io.*;

/*
Как сериализовать static?
Сделай так, чтобы сериализация класса ClassWithStatic была возможной.


Требования:
1. Класс ClassWithStatic должен существовать внутри класса Solution.
2. Класс ClassWithStatic должен быть статическим.
3. Класс ClassWithStatic должен быть публичным.
4. Класс ClassWithStatic должен поддерживать интерфейс Serializable.
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "This is a static test string";
        public int i;
        public int j;

    }

    public static void main(String[] args) throws Exception{
//        ClassWithStatic classWithStatic = new ClassWithStatic();
//        classWithStatic.i = 12;
//        classWithStatic.j = 66;
//        System.out.println("объект до сериализации: ");
//        System.out.println(classWithStatic.toString());
//        System.out.println(classWithStatic.hashCode());
//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2009\\1")));
//        oos.writeObject(classWithStatic);
//
//        ObjectInputStream ois = new ObjectInputStream((new FileInputStream(new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2009\\1"))));
//
//        ClassWithStatic classWithStatic2 = (ClassWithStatic) ois.readObject();
//        System.out.println("объект после сериализации: ");
//        System.out.println(classWithStatic2.toString());
//        System.out.println(classWithStatic2.hashCode());

    }
}
