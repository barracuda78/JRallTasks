package com.javarush.task.task25.task2504;

/* 
Switch для нитей
1. Если нить еще не запущена, то запусти ее.
2. Если нить в ожидании, то прерви ее.
3. Если нить работает, то проверь маркер isInterrupted.
4. Если нить прекратила работу, то выведи в консоль ее приоритет.
*/
public class Solution {
    public static void processThreads(Thread... threads) {
        for(Thread thread : threads){
            Thread.State state = thread.getState();

            switch (state) {
                case NEW:
                    thread.start();
                    break;
                case WAITING:
                    thread.interrupt();
                    break;
                case TIMED_WAITING:
                    thread.interrupt();
                    break;
                case RUNNABLE:
                    thread.isInterrupted();
                    break;
                case TERMINATED:
                    System.out.println(thread.getPriority());
                    break;
                case BLOCKED:
                    thread.interrupt();
                    break;
            }
        }
        //implement this method - реализуйте этот метод
    }

    public static void main(String[] args) {
    }
}
