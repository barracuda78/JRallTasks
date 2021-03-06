package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;

public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //4.1. Используя метод offer добавь в очередь 9 ShareItem-ов с такими параметрами: ("ShareItem-N", N), где N - номер элемента от 1 до 9.

            for (int i = 1; i <= 9; i++) {
                StringBuilder sb = new StringBuilder("ShareItem-");
                sb.append(i);
                //4.2. Перед каждым добавлением выведи фразу "Элемент 'ShareItem-N' добавлен". Используй System.out.format.
                //System.out.format("Элемент '%s' добавлен\n", sb.toString());
                System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);

                queue.offer(new ShareItem(sb.toString(), i));

                //4.3. Усыпи трэд на 0.1 секунды.
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //4.4. Если у очереди есть Consumer, не занятый работой, то выведи фразу "Consumer в ожидании!".
                //Просмотри методы интерфейса TransferQueue, там есть нужный метод.
                if (queue.hasWaitingConsumer()) {
                    System.out.format("Consumer в ожидании!");
                    //System.out.println("Consumer в ожидании!");
                }
            }
    }
}
