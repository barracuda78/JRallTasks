package com.javarush.task.task27.task2709;

public class ConsumerTask implements Runnable {
    private TransferObject transferObject;
    protected volatile boolean stopped;

    public ConsumerTask(TransferObject transferObject) {
        this.transferObject = transferObject;
        new Thread(this, "ConsumerTask").start();
    }

    //В методах run классов ConsumerTask и ProducerTask создай необходимые synchronized блоки.
    public void run() {
            while (!stopped) {
                transferObject.get();
            }
    }

    public void stop() {
        stopped = true;
    }
}