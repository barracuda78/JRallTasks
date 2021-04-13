package com.javarush.task.task27.task2707;

/*
Определяем порядок захвата монитора
*/

//Создаем 2 нити.
// В первой создаем synchronized по о1, потом слип на 500 мс, внутри ставим второй synchronized по о2.
// Во второй нити просто вызываем someMethodWithSynchronizedBlocks(o1, o2);
// запускаем первую нить, запускаем вторую нить и ставим на паузу на 2 секунды.
// Проверяем состояние второй нити: если НЕ blocked - возвращаем тру, иначе фолс.

//Логика такова. Мы специально создаем дедлок. Если в даном нам методе синхронизация происходит сначала по 1 объекту, потом по 2, значит дедлока не будет и наш метод просто подождет немного и выполнится, иначе образуется дедлок и вторая нить станет BLOCKED. Надеюсь понятно объяснил)

public class Solution {

    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj1) {
            synchronized (obj2) {
                System.out.println(obj1 + " " + obj2);
            }
        }
    }

    public static boolean isLockOrderNormal(final Solution solution, final Object o1, final Object o2) throws Exception {
        Thread thread1 = new Thread(()-> {
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2){

                }
            }
        });

        Thread thread2 = new Thread(()-> solution.someMethodWithSynchronizedBlocks(o1, o2));

        thread1.setDaemon(true);
        thread2.setDaemon(true);
        thread1.start();
        thread2.start();
        Thread.sleep(2000);

        //return thread2.getState().equals(Thread.State.BLOCKED) ? false : true;
        return (thread2.getState() != Thread.State.BLOCKED);
        //do something here
        //return false;
    }

    public static void main(String[] args) throws Exception {
        final Solution solution = new Solution();
        final Object o1 = new Object();
        final Object o2 = new Object();

        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
