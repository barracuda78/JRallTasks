package com.javarush.task.task24.task2412;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        WaitAndNotify wn = test.new WaitAndNotify();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                wn.produce();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                wn.consume();
            }
        });

        thread1.start();
        thread2.start();




        thread1.join();
        thread2.join();
    }

    class WaitAndNotify{
        public void produce(){
            synchronized (this){
                System.out.println("producer has been started...");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("producer resumed.");
            }
        }

        public void consume(){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("this is consumer");
            System.out.println("please press enter key...");
            Scanner scanner = new Scanner(System.in);
            synchronized (this){
                scanner.nextLine();
                notify();
            }
        }
    }
}
