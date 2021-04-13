package com.javarush.task.task04.task0441;


/* 
Как-то средненько
*/
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String numbers = bufferedReader.readLine();
        int a = Integer.parseInt(numbers);
        String numbers2 = bufferedReader.readLine();
        int b = Integer.parseInt(numbers2);
        String numbers3 = bufferedReader.readLine();
        int c = Integer.parseInt(numbers3);

        if(a < b & a < c) {
            if(b < c) {
                System.out.println(b);
            }
            else {
                System.out.println(c);
            }
        }
        else{
            if(b < a & b < c){
                if(a < c){
                    System.out.println(a);
                }
                else{
                    System.out.println(c);
                }
            }
            else{
                if(c < a & c < b){
                    if(a < b) {
                        System.out.println(a);
                    }
                    else{
                        System.out.println(b);
                    }
                }
                else{
                    if (a==b){
                        System.out.println(a);
                    }
                    else{
                        if(a==c){
                            System.out.println(a);
                        }
                        else{
                            if(b==c){
                                System.out.println(b);
                            }
                            else{
                                System.out.println(a);
                            }
                        }
                    }
                }
            }
        }
    }
}
