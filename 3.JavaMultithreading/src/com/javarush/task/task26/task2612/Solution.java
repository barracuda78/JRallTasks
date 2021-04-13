package com.javarush.task.task26.task2612;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
Весь мир играет комедию
*/
public class Solution {
    private Lock lock = new ReentrantLock();

    //Убедись, что метод someMethod() не вызывает unlock(), если tryLock() вернул false.
    public void someMethod() {
        // Implement the logic here. Use the lock field
        lock.lock();
        if(lock.tryLock()){
            try {
                actionIfLockIsFree();
            }
            catch(Exception e){
                lock.unlock();
            }
        } else{
            actionIfLockIsBusy();
            //lock.unlock();
        }

    }

    public void actionIfLockIsFree() {
    }

    public void actionIfLockIsBusy() {
    }
}
