package com.javarush.task.task35.task3507;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyClassLoader extends ClassLoader{

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        Class clazz = null;

        try {
            byte[] buffer = Files.readAllBytes(Paths.get(name));
            clazz = defineClass(null, buffer, 0, buffer.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clazz;
    }
}