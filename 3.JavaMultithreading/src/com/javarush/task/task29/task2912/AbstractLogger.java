package com.javarush.task.task29.task2912;

//3. Необходимо поднять поля level и next в класс AbstractLogger.
//4. Необходимо поднять методы setNext(Logger) и inform(String, int) в класс AbstractLogger.
public abstract class AbstractLogger implements Logger {
    int level;
    Logger next;

    public AbstractLogger(int level){
        this.level = level;
    }

    @Override
    public void inform(String message, int level) {
        if (this.level <= level) {
            info(message);
        }
        if (next != null) {
            next.inform(message, level);
        }
    }

    @Override
    public void setNext(Logger next) {
        this.next = next;
    }
}
