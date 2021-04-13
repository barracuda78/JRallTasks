package com.javarush.task.task21.task2105;

public class Factorial {
    public static void main(String[] args) {
        System.out.println("Ответ: " + factorial(5));
    }

    public static int factorial(int n){
        int result;

        if(n == 1) return 1;

        result = factorial(n-1)*n;

        return  result;
    }
}
