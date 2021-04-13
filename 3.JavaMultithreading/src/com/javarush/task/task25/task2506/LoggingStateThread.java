package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread target;


    public LoggingStateThread(Thread target){
        this.target = target;
        //setDaemon(true);
    }

//4. После запуска класс LoggingStateThread должен выводить в консоль изменения состояния переданной нити.
//5. После завершения работы наблюдаемой нити, LoggingStateThread так же должен завершить работу.
//    если состояние нити отличается от старого, выведите его на экран. Если состояние TERMINATED, закончите цикл.
//    Мониторим в бесконечном цикле изменение состояния target, и рвем цикл когда его состояние станет равно INTERRUPTED
    @Override
    public void run(){
        Thread.State state1 = target.getState();
        System.out.println(state1);
        while(true){
            Thread.State state2 = target.getState();

            if(state1 != state2){
                System.out.println(state2);
                state1 = state2;
            }

            if(state2 == State.TERMINATED){
                return;
            }
        }


    }

}
