package com.javarush.task.task29.task2913;

import java.util.Random;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {

        if (a > b) {
            StringBuilder sb = new StringBuilder();
            for(int i = a; i >= b; i--){
                sb.append(i);
                sb.append(" ");
            }
            return sb.toString().trim();
        } else if( b > a){
            StringBuilder sb = new StringBuilder();
            for(int i = a; i <= b; i++){
                sb.append(i);
                sb.append(" ");
            }
            return sb.toString().trim();
        }
        else {
            return Integer.toString(a);
        }


    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}