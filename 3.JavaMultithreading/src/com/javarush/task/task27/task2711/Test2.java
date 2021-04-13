package com.javarush.task.task27.task2711;



import java.util.concurrent.LinkedBlockingQueue;

public class Test2 {

    private static final LinkedBlockingQueue<String> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws InterruptedException {
        int size = 2;
        int k = (int)((Math.random()) * size);

        System.out.println(k);

        orderQueue.put("Привет");

        //orderQueue = new LinkedBlockingQueue<>();
    }
}
