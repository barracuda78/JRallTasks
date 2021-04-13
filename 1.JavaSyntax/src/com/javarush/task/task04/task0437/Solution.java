package com.javarush.task.task04.task0437;


/* 
Треугольник из восьмерок
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        //int row = 1;
        //int line = 1;
        int n = 1;

        for(int line = 1; line < 11; line++) {

            for (int row = 1; row < n; row++) {
                System.out.print("8");
            }
            n++;
            System.out.println("8");
        }
    }
}