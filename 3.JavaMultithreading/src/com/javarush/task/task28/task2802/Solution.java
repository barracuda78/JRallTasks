package com.javarush.task.task28.task2802;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Пишем свою ThreadFactory
*/
public class Solution {

    public static class AmigoThreadFactory implements ThreadFactory{

        final static AtomicInteger poolNumber = new AtomicInteger(1);
        final ThreadGroup GN;
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final String poolName;

        public AmigoThreadFactory(){
            SecurityManager sm = System.getSecurityManager();
            //GN = (sm != null)? sm.getThreadGroup() : Thread.currentThread().getThreadGroup();
            GN = Thread.currentThread().getThreadGroup();
            poolName = String.valueOf(poolNumber.getAndIncrement());
        }


        @Override
        public Thread newThread(Runnable r){


            Thread thread = new Thread(GN, r, Thread.currentThread().getThreadGroup().getName() + "-pool-" + poolName + "-thread-" + threadNumber.getAndIncrement());
            if (thread.isDaemon())
                thread.setDaemon(false);
            if(thread.getPriority() != Thread.NORM_PRIORITY)
                thread.setPriority(Thread.NORM_PRIORITY);

            //1.3. имя трэда должно иметь шаблон "GN-pool-A-thread-B",
            //где GN - это имя группы,
            //A - это номер фабрики инкрементируется в пределах класса начиная с 1, используйте AtomicInteger,
            //B - номер треда инкрементируется в пределах конкретной фабрики начиная с 1, используйте AtomicInteger.
            //2. Каждая фабрика должна иметь ту группу тредов (ThreadGroup), в которой она была создана.

            //thread.setName("");

            return thread;
        }

//        @Override
//        public String toString(){
//            return Thread.currentThread().getThreadGroup().getName() + "жопа";
//        }

    }


    public static void main(String[] args) {
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        thread.start();
        thread2.start();
    }

    private static void emulateThreadFactory() {
        AmigoThreadFactory factory = new AmigoThreadFactory();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }
}
