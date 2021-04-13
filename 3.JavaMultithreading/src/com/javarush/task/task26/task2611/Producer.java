package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer(ConcurrentHashMap<String, String> map){
        this.map = map;
    }

    //6. Метод run() класса Producer должен при возникновении исключения выводить в консоль "[THREAD_NAME] thread was terminated".
    @Override
    public void run(){
        try {


        for(int i = 1; ; i++){
            map.put(String.valueOf(i), "Some text for " + i);
            Thread.sleep(500);
        }

        } catch (InterruptedException e) {
            //pool-1-thread-1 thread was terminated
            String s = Thread.currentThread().getName() + " thread was terminated";
            String s2 = s.substring(s.indexOf('-')+1);
            String s3 = s2.substring(s2.indexOf('-')+1);
            System.out.println(s3);

        }

    }
}
