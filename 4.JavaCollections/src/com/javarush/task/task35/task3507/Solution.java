package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
//ClassLoader - что это такое?
//Реализуй логику метода getAllAnimals.
//Аргумент метода pathToAnimals - это абсолютный путь к директории, в которой хранятся скомпилированные классы.
//Путь не обязательно содержит / в конце.
//НЕ все классы наследуются от интерфейса Animal.
//НЕ все классы имеют публичный конструктор без параметров.
//Только для классов, которые наследуются от Animal и имеют публичный конструктор без параметров, - создать по одному объекту.
//Добавить созданные объекты в результирующий сет и вернуть.
//Метод main не участвует в тестировании.
//
//
//Требования:
//1. Размер множества возвращаемого методом getAllAnimals должен быть равен количеству классов поддерживающих интерфейс Animal и имеющих публичный конструктор без параметров (среди классов расположенных в директории переданной в качестве параметра).
//2. В множестве возвращаемом методом getAllAnimals должны присутствовать все классы поддерживающие интерфейс Animal и имеющие публичный конструктор без параметров (среди классов расположенных в директории переданной в качестве параметра).
//3. В множестве возвращаемом методом getAllAnimals НЕ должен присутствовать ни один класс не поддерживающий интерфейс Animal или не имеющий публичного конструктора без параметров (среди классов расположенных в директории переданной в качестве параметра).
//4. Метод getAllAnimals должен быть статическим.
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        if (!pathToAnimals.endsWith("\\") && !pathToAnimals.endsWith("/"))
            pathToAnimals = pathToAnimals + "/";

        Set<Animal> set = new HashSet<>();
        File[] dir = new File(pathToAnimals).listFiles();

        for (File f : dir) {
            try {
                if (f.isFile() && f.getName().contains(".class")) {
                    MyClassLoader loader = new MyClassLoader();
                    Class<?> clazz = loader.loadClass(f.getAbsolutePath());

                    if (Animal.class.isAssignableFrom(clazz)) {
                        Constructor<?> constructor = clazz.getConstructor();
                        set.add((Animal) constructor.newInstance());
                    }
                }
            } catch (Exception e) {
            }
        }
        return set;
    }
}
