package com.javarush.task.task04.task0416;

/*
Переходим дорогу вслепую
*/

import java.io.*;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        //InputStream inputStream = System.in;
        //Reader inputStreamReader = new InputStreamReader(inputStream);
       // BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

       // String s = bufferedReader.readLine();
       // int t = Integer.parseInt(s);

        Scanner sc = new Scanner(System.in);
        double t = sc.nextDouble();
        sc.close();

        if((t+1)%5 == 0) {
            System.out.println("красный");
        }
        else {
            if((t+2)%5 == 0) {
                System.out.println("жёлтый");
            }
            else {
                System.out.println("зелёный");
            }
        }
    }
}
