package com.javarush.task.task24.task2411;

import java.util.Scanner;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
//        int day = (int) (Math.RANDOM() * 28);
//        System.out.println("day " + day);
//
//
//        int month = (int) (Math.RANDOM() * 12);
//        System.out.println("month " + month);
//
//        int year = 1970 + (int) (Math.RANDOM() * 30);
//        System.out.println("year " + year);


        WaitAndNotify wn = new WaitAndNotify();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.produce();
                } catch (InterruptedException e) {


                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wn.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }


    static class WaitAndNotify{
        public void produce() throws InterruptedException {
            synchronized (this){
                System.out.println("produce() is running...");
                wait();
                System.out.println("produce() resumed...");

            }
        }

        public void consume() throws InterruptedException {
            Scanner s = new Scanner(System.in);
            System.out.println("consume() is running...");
            Thread.sleep(100);
            synchronized (this){
                System.out.println("waiting for key pressed...");
                s.nextLine();
                notify();
                System.out.println("notified. Wait 5 sec pls...");
                Thread.sleep(5000);
            }
        }
    }
}
