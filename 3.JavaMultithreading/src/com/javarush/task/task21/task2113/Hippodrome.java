package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Hippodrome {
    private static List<Horse> horses;
    static Hippodrome game;

    public List<Horse> getHorses(){
        return horses;
    }

    public Hippodrome(List<Horse> horses){
        this.horses = horses;
    }

    public void run(){
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void move(){
        for(Horse horse : getHorses()){
            horse.move();
        }
    }
    public void print(){
        for(Horse horse : getHorses()){
            horse.print();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public Horse getWinner(){
        double maxDistance = Double.MIN_VALUE;
        int winnerIndex = -1;
        for(int i = 0; i < horses.size(); i++){
            if (horses.get(i).getDistance() > maxDistance) {
                maxDistance = horses.get(i).getDistance();
                winnerIndex = i;
            }
        }
        return horses.get(winnerIndex);
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }


    public static void main(String[] args) {
        horses = new ArrayList<>();
        game = new Hippodrome(horses);
        Horse sivka = new Horse("Sivka", 3.0, 0.0);
        Horse burka = new Horse("Burka", 3.0, 0.0);
        Horse kaurka = new Horse("Kaurka", 3.0, 0.0);
        horses.add(sivka);
        horses.add(burka);
        horses.add(kaurka);

        game.run();
        game.printWinner();
    }
}
