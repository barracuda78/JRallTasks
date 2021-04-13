package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/
//Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
//Описание класса:
//1. Реализует интерфейс List;
//2. Является приватным статическим классом внутри популярного утилитного класса;
//3. Доступ по индексу запрещен - кидается исключение IndexOutOfBoundsException.
//Используя рефлекшн (метод getDeclaredClasses), верни подходящий тип в методе getExpectedClass.
//
//
//Требования:
//1. Метод getExpectedClass должен использовать метод getDeclaredClasses подходящего утилитного класса.
//2. Метод getExpectedClass должен вернуть правильный тип.
//3. Метод main должен вызывать метод getExpectedClass.
//4. Метод main должен вывести полученный класс на экран.
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        //Получаю массив личных классо у Collections
        Class[] classes = Collections.class.getDeclaredClasses();

        // Обхожу их
        for (Class clazz : classes) {
            //Проверяю реализует ли он или его родитель интерфейс List
            if (List.class.isAssignableFrom(clazz) || List.class.isAssignableFrom(clazz.getSuperclass())) {
                //Проверяю есть ли у него модификатор static и private
                if (Modifier.isPrivate(clazz.getModifiers()) && Modifier.isStatic(clazz.getModifiers())) {
                    try {
                        //Получаю метод get и открываю к нему доступ
                        Method method = clazz.getDeclaredMethod("get", int.class);
                        method.setAccessible(true);

                        //Получаю конструктор без параметров и открываю к нему доступ
                        //Constructor constructor = clazz.getConstructor();
                        Constructor constructor = clazz.getDeclaredConstructor();
                        constructor.setAccessible(true);

                        //Вызываю метод с обьектом созданным конструктором и пытаюсь get(1)
                        method.invoke(constructor.newInstance(), 1);
                    } catch (InvocationTargetException e) {
                        //Если ловлю этот ексепшни и в  тексте его причины есть IndexOutOfBoundsException то возвращаю это нужный класс
                        if (e.getCause().toString().contains( "IndexOutOfBoundsException")) {
                            return clazz;
                        }
                    } catch (Exception e) {

                    }
                }
            }
        }
        return null;
    }
}
