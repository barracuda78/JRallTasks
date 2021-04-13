package com.javarush.task.task20.task2018;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) {
     MyClass myClass = null;
     try{
         Class clazz = Class.forName((MyClass.class.getName()));
         myClass = (MyClass) clazz.newInstance();

     }
     catch(ClassNotFoundException | IllegalAccessException | InstantiationException e){
         e.printStackTrace();
     }
        System.out.println(myClass);
    }

    public static void printData(Object myClass){
        try{
            Method method = myClass.getClass().getDeclaredMethod("printData");
            method.setAccessible(true);
            method.invoke(myClass);
        }
        catch(NoSuchMethodException |IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }
}
