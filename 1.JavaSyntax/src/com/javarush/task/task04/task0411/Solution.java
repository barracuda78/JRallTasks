package com.javarush.task.task04.task0411;

/* 
Времена года на Терре
*/

public class Solution {
    public static void main(String[] args) {
        checkSeason(12);
        checkSeason(4);
        checkSeason(7);
        checkSeason(10);
    }

    public static void checkSeason(int month) {
        //напишите тут ваш код
        if(2 < month && month <6) {
            System.out.println("весна");
        }
        else{
            if(5 < month && month <9) {
                System.out.println("лето");
            }
            else{
                if(8 < month && month < 12) {
                    System.out.println("осень");
                }
                else {
                    System.out.println("зима");
                }
            }
        }
    }
}