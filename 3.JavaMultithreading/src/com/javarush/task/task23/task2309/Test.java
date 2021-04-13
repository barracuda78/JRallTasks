package com.javarush.task.task23.task2309;




public class Test {

    static class Ruzaev{

    }

    static class Andrey extends  Ruzaev{

        public void startAndrey(){
            Thread thread = new Vladimirovich();
            thread.start();
        }

        class Vladimirovich extends Thread{
            @Override
            public void run(){
                System.out.println("Andrey is running...");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Andrey andrey = new Andrey();
        andrey.startAndrey();
    }
}
