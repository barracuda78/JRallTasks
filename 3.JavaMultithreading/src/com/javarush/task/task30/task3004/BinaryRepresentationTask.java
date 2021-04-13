package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {

    private int i;

    public BinaryRepresentationTask(int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        //4. Метод compute() должен правильно переводить число в двоичную систему счисления.
        //3. В классе BinaryRepresentationTask в методе compute() используй методы fork() и join().
        //4. Пример функциональной реализации:
        //
        //    private String binaryRepresentationMethod(int x) {
        //        int a = i % 2;                                     //    a = 6%2 = 0
        //        int b = i / 2;                                     //    b = 6/2 = 3
        //        String result = String.valueOf(a);                 //    result = "0"
        //        if (b > 0) {                                       //    b > 0
        //            return binaryRepresentationMethod(b) + result; //    result = ... + "0"
        //        }
        //        return result;
        //    }
        int a = i % 2;
        int b = i / 2;
        String result = String.valueOf(a);

        if(b > 0) {
            BinaryRepresentationTask binaryRepresentationTask = new BinaryRepresentationTask(b);
            binaryRepresentationTask.fork();
            result = binaryRepresentationTask.join() + result;
        }
        return result;
    }
}
