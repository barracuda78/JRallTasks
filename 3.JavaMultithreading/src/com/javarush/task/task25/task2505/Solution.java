package com.javarush.task.task25.task2505;

/* 
Без дураков
*/
public class Solution {

    public static void main(String[] args) {
        MyThread myThread = new Solution().new MyThread("super secret key");
        myThread.start();
    }

    public class MyThread extends Thread {
        private String secretKey;

        public MyThread(String secretKey) {
            this.secretKey = secretKey;
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            //setDaemon(true);
        }

        @Override
        public void run() {
            throw new NullPointerException("it's an example");
        }

//1. Создай private class MyUncaughtExceptionHandler, который на перехват исключения должен подождать половину секунды,
// а затем вывести на экран secretKey, имя трэда и сообщение возникшего исключения.
//Используй String.format(...).

//Пример:
//super secret key, Thread-0, it's an example
        private class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{
            MyUncaughtExceptionHandler(){

            }
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                System.out.println(String.format("%s, %s, %s", secretKey, t.getName(), e.getMessage()));
                //System.out.println(secretKey + ", " + t.getName() + ", " + e.getMessage());
            }
        }
    }
}

