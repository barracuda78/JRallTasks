package com.javarush.task.task26.task2603;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test{
    private static int counter;

    public static synchronized void incrementCounter(){
        counter++;
    }


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(9);

        for (int i = 0; i < 1000; i++) {
            executorService.submit(new MyRunnable());
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter);
    }


}

class MyRunnable implements Runnable{
    @Override
    public void run(){
        Test.incrementCounter();
    }
}