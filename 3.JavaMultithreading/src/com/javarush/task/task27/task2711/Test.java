package com.javarush.task.task27.task2711;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Singleton singleton = Singleton.getInstance();
//        System.out.println(singleton.getID().toString());
//        Singleton singleton2 = Singleton.getInstance();
//        System.out.println(singleton2.getID().toString());
        ExecutorService executorService = Executors.newFixedThreadPool(150);

        for(int i = 0; i < 15; i++){
            Future<Singleton> futureTask = executorService.submit(new MyCallable());
            System.out.println(futureTask.get());
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.DAYS);

    }
}

class Singleton{
    private static volatile Singleton instance;
    private static AtomicInteger counter = new AtomicInteger(0);
    private AtomicInteger id;

    private Singleton(){
        counter.incrementAndGet();
        this.id = counter;
    }

    public static Singleton getInstance(){
        if(instance==null){
//            synchronized (Singleton.class){
 //               if (instance == null){
                    instance = new Singleton();
                    return instance;
//                }
//            }
        }
        return instance;
    }

    public AtomicInteger getID(){
        return id;
    }
}

class MyCallable implements Callable<Singleton> {

    @Override
    public Singleton call() {
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.getID().toString());
        return singleton;
    }
}