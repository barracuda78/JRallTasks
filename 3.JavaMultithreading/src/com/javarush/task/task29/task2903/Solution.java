package com.javarush.task.task29.task2903;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;

/* 
И еще раз рефакторинг Ӏ Java Multithreading: 9 уровень, 5 лекция
*/

public class Solution {
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static void main(String[] args) {
        ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<>();
        for (int i = 0; i < 100; i++) {
            new Thread(getRunnable(i, concurrentMap)).start();
        }
        sleepOneSecond();
    }

    private static void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Runnable getRunnable(final int i, final ConcurrentMap<Integer, String> concurrentMap) {
        return new Runnable() {
            @Override
            public void run() {
                final String name = "Thread #" + i;
                int randomInt = RANDOM.nextInt(20);
                String text = name + " вставил запись для " + randomInt;

                // previousEntry is null for new entries
                /* Instead of setting it to null, call concurrentMap.someMethod(randomInt, text) */
                //3. В строке "String previousEntry = null;" у concurrentMap вызови метод, который вставит пару (randomInt, text) только для ключа, которого нет в concurrentMap.
                //putIfAbsent(K key, V value) :
                //If the specified key is not already associated with a value, associate it with the given value.
                //(Вызванный метод должен возвращать предыдущее значение либо null для новой пары.)
                //3. Метод run() внутри метода getRunnable() должен вызывать у concurrentMap метод, вставляющий пару (randomInt, text), если в concurrentMap еще нет пары со значением ключа randomInt.
                //4. Метод run() класса, возвращаемого методом getRunnable(), должен выводить текст на экран.
                String previousEntry = concurrentMap.putIfAbsent(randomInt, text);

                if (previousEntry != null) {
                    System.out.println(name + " хочет обновить " + randomInt + ", однако уже " + previousEntry);
                } else {
                    System.out.println(text);
                }
            }
        };
    }
}
