package com.javarush.task.task25.task2508;

//Восстанови логику класса TaskManipulator.
//
//Требования:
//1. Класс TaskManipulator должен реализовывать интерфейсы Runnable и CustomThreadManipulator.
//2. Метод run должен каждые 100 миллисекунд выводить имя исполняемой нити в консоль.
//3. Класс TaskManipulator должен иметь внутреннее поле типа Thread.
//4. Метод start должен создавать, сохранять во внутреннее поле и запускать нить с именем, которое передано через аргумент метода.
//5. Метод stop должен прерывать последнюю созданную классом TaskManipulator нить.
public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread t;
    @Override
    public void run() {
        while(!t.isInterrupted()) {
            System.out.println(t.getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

                break;
            }
        }

    }

    ////4. Метод start должен создавать, сохранять во внутреннее поле и запускать нить с именем, которое передано через аргумент метода.
    @Override
    public void start(String threadName) {
        Thread t = new Thread(this, threadName);
        this.t = t;
        this.t.start();
    }

    //5. Метод stop должен прерывать последнюю созданную классом TaskManipulator нить.
    @Override
    public void stop() {
        t.interrupt();
    }
}
