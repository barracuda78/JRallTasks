package com.javarush.task.task25.task2509;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class SocketTask<T> implements CancellableTask<T> {
    private Socket socket;

    protected synchronized void setSocket(Socket socket) {
        this.socket = socket;
    }

    //2. Метод cancel в классе SocketTask должен закрывать используемые классом ресурсы.
    public synchronized void cancel() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //close all resources here
    }

    public RunnableFuture<T> newTask() {
        return new FutureTask<T>(this) {
            //3. Метод cancel для локального класса внутри метода newTask должен закрывать ресурсы SocketTask и вызвать cancel у родительского класса.
            //4. Метод у родительского класса должен быть вызван, даже если во время закрытия ресурсов было выкинуто исключение.

            //close all resources here by using proper SocketTask method
            //call super-class method in finally block
            public boolean cancel(boolean mayInterruptIfRunning) {

                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        super.cancel(true);
                    }
                    return false;
                }
        };
    }
}