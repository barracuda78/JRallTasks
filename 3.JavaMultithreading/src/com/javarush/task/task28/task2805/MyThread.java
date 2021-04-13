package com.javarush.task.task28.task2805;

public class MyThread extends Thread {
    private static int priority;

    public MyThread() {
        if (priority == 10)
            priority = 0;
        priority++;
        this.setPriority(priority);
    }

    public MyThread(Runnable target) {
        super(target);
        if (priority == 10)
            priority = 0;
        priority++;
        this.setPriority(priority);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        if (priority == 10)
            priority = 0;
        priority++;
        this.setPriority(priority);
    }

    public MyThread(String name) {
        super(name);
        if (priority == 10)
            priority = 0;
        priority++;
        this.setPriority(priority);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        if (priority == 10)
            priority = 0;
        priority++;

        this.setPriority(priority);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        if (priority == 10)
            priority = 0;
        priority++;
        this.setPriority(priority);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        if (priority == 10)
            priority = 0;
        priority++;
        this.setPriority(priority);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        if (priority == 10)
            priority = 0;
        priority++;
        this.setPriority(priority);
    }

}
