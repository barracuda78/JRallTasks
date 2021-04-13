package com.javarush.task.task23.task2308;

public class Test {

    public static void main(String[] args) {

    }
    class Cat{}

    class Tiger extends Cat
    {
        public void tigerRun()
        {
            System.out.println("Tiger is running...");
        }

        public void startTiger()
        {
            TigerThread thread = new TigerThread();
            thread.start();
        }

        class TigerThread extends Thread
        {
            public void run()
            {
                tigerRun();
            }
        }
    }
}
