package com.javarush.task.task27.task2709;


//В классе TransferObject расставь вызовы методов wait/notify/notifyAll, чтобы обеспечить последовательное создание и получение объекта.
public class TransferObject {

    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    public synchronized int get() {
            while(!isValuePresent){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Got: " + value);
            isValuePresent = false;
            notifyAll();
        return value;
    }

//    public int get() {
//        synchronized (this) {
//            while(!isValuePresent){
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("Got: " + value);
//            isValuePresent = false;
//            notifyAll();
//        }
//        return value;
//    }



    public synchronized void put(int value) {
            while(isValuePresent){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            this.value = value;
            isValuePresent = true;
            notifyAll();
        System.out.println("Put: " + value);
    }

//    public void put(int value) {
//
//        synchronized (this) {
//            while(isValuePresent){
//                try {
//                    wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            this.value = value;
//            isValuePresent = true;
//            notifyAll();
//        }
//        System.out.println("Put: " + value);
//    }
}