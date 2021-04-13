package com.javarush.task.task34.task3411;

/* 
Ханойские башни
*/


public class Solution {
    public static void main(String[] args) {
        int numRings = 3;
        moveRing('A', 'B', 'C', numRings);
    }

    public static void moveRing(char fromA, char toB, char helpC, int numRings) {
        if(numRings > 0){
            moveRing(fromA, helpC, toB, numRings - 1);
            System.out.println("from " + fromA + " to " + toB);
            moveRing(helpC, toB, fromA, numRings - 1);
        }
    }
    //Рекурсивно решаем задачу «перенести башню из n−1 диска на 2-й штырь».
    // Затем переносим самый большой диск на 3-й штырь,
    // и рекурсивно решаем задачу «перенеси башню из n−1 диска на 3-й штырь».



}