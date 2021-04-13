package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
CountDownLatch
*/
public class Solution implements Runnable{
//1. Из класса Solution должно быть удалено поле lock.
//2. Из метода someMethod должен быть удален synchronized блок.
//3. В методе someMethod должен быть вызван метод await без параметров у объекта сохраненного в поле latch.
//4. В методе someMethod должен быть вызван метод retrieveValue.
//5. В методе someMethod должен быть вызван метод countDown у объекта сохраненного в поле latch.

//    private final Object lock = new Object();
    private volatile boolean isWaitingForValue = true;

    CountDownLatch latch = new CountDownLatch(1);

    public void someMethod() throws InterruptedException {
//        synchronized (lock) {
//            while (isWaitingForValue) {
//                lock.wait();
//            }

            latch.countDown();
              retrieveValue();
            latch.await();

//            isWaitingForValue = false;
//            lock.notify();
//        }
    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }


    @Override
    public void run() {
        try {
            someMethod();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        //создаем объект
//        Solution solution = new Solution();
//        //создаем поток
//        Thread t1 = new Thread(solution);
//        t1.start();
//
//        //просто считаем до пяти.
//        for (int i = 0; i < 5; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(i + "...");
//        }
//
//        synchronized (solution.lock) {
//            solution.isWaitingForValue = false;
//            solution.lock.notify();
//        }
    }
}
