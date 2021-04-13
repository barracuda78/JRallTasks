package com.javarush.task.task25.task2504;


public class Test{

    public class MyException extends Exception{
//        public MyException(){
//            this.initCause()
//        }
    }

    private double divide(int a, int b) throws ArithmeticException{
        if(b == 0) {
            Exception e = new MyException();
            ArithmeticException ar = new ArithmeticException("эй, на ноль делить нелььзя");
            ar.initCause(e);
            throw ar;
        }
        return a/b;
    }

    public class MyHandler implements Thread.UncaughtExceptionHandler{
        @Override
        public void uncaughtException(Thread t, Throwable e){
            System.out.println("это исключение, и его причина: " + e.getCause() + " и его сообщение: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(new Test().divide(5, 0)));
        thread.setUncaughtExceptionHandler(new Test().new MyHandler());
        thread.start();
    }
}
