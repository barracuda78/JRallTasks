package com.javarush.task.task25.task2512;

/* 
Живем своим умом
java.lang.IllegalAccessException: GHI
java.lang.RuntimeException: DEF
java.lang.Exception: ABC
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    private static void method(Throwable throwable){
        if(throwable == null) return;
        method(throwable.getCause());
        System.out.println(throwable);

        //return throwable.getCause();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        method(e);
    }

    public static void main(String[] args) {
        new Solution().uncaughtException(Thread.currentThread(),new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
