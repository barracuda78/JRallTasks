package com.javarush.task.task21.task2112;

import java.lang.reflect.Method;
public class Test {
    public static void main(String[] args) throws ClassNotFoundException{

            Class cls = Class.forName("com.javarush.task.task21.task2112.Test2");
            Method[] methods = cls.getDeclaredMethods();
            for(Method m : methods){
                System.out.println(m);
            }

    }
}
