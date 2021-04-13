package com.javarush.task.task19.task1903;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        for(int i = 0; i<4; i++){
            one: {
                two: {
                    three:{
                        System.out.println("from three block " + i);
                        break three;

                    }
                    System.out.println("from two block " + i);
                }
                System.out.println("from one block " + i);
            }
            System.out.println("from FOR cycle " + i);
        }
    }
}
