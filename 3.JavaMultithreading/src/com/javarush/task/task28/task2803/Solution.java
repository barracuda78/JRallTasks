package com.javarush.task.task28.task2803;

import java.util.concurrent.ThreadLocalRandom;

/* 
ThreadLocalRandom
Класс Solution будет использоваться трэдами.
Реализуй логику всех методов, используйте класс ThreadLocalRandom.

//ThreadLocalRandom.current().nextX(...) (where X is Int, Long, etc)

*/
public class Solution {
    //getRandomIntegerBetweenNumbers должен возвращать случайный int между from и to.
    public static int getRandomIntegerBetweenNumbers(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }

    //getRandomDouble должен возвращать случайный double.
    public static double getRandomDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    //getRandomLongBetween0AndN должен возвращать случайный long между 0 и n.
    public static long getRandomLongBetween0AndN(long n) {
        return ThreadLocalRandom.current().nextLong(n);
    }

    public static void main(String[] args) {
    }
}
