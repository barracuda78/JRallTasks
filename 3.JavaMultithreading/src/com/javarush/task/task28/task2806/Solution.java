package com.javarush.task.task28.task2806;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/* 
Знакомство с Executors
*/
public class Solution {

    public static void main(String[] args) throws InterruptedException {

        Solution solution = new Solution();
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            executorService.submit(solution.new MyRunnable());
        }

        executorService.shutdown();

        executorService.awaitTermination(5, TimeUnit.SECONDS);

        //Add your code here

        /* output example
pool-1-thread-2, localId=2
pool-1-thread-1, localId=1
pool-1-thread-3, localId=3
pool-1-thread-1, localId=7
pool-1-thread-1, localId=9
pool-1-thread-4, localId=4
pool-1-thread-5, localId=5
pool-1-thread-2, localId=6
pool-1-thread-1, localId=10
pool-1-thread-3, localId=8
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }

    private AtomicInteger counter = new AtomicInteger(0);

    class MyRunnable implements Runnable{

        //private int cnt = 0;
        @Override
        public void run() {
            doExpensiveOperation(counter.incrementAndGet());
            //doExpensiveOperation(cnt++);
        }
    }
}

