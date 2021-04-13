package com.javarush.task.task25.task2510;

/* 
Поживем - увидим
1. Если это Error, то вывести в консоль "Нельзя дальше работать".
2. Если это Exception, то вывести в консоль "Надо обработать".
3. Если это Throwable, то вывести в консоль "Поживем - увидим".
Реализуй эту логику.
*/
public class Solution extends Thread {
//    private double divide(int a, int b){
//        return a/b;
//    }
//    @Override
//    public void run(){
//        System.out.println("solution is running...");
//        double d = divide(5, 0);
//        System.out.println("solution is ending...");
//
//    }

    public Solution() {
        this.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if(e instanceof Error){
                    System.out.println("Нельзя дальше работать");
                } else
                if(e instanceof Exception){
                    System.out.println("Надо обработать");
                } else
                if(e instanceof Throwable){
                    System.out.println("Поживем - увидим");
                }
            }
        });
    }

    public static void main(String[] args) {
//        Solution s = new Solution();
//        s.start();
    }
}
