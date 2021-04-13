package com.javarush.task.task21.task2113;

public class Horse implements Comparable<Horse>{
    private String name;
    private double speed;
    private double distance;

    public Horse(String name, double speed, double distance){
        this.name = name;
        this.speed = speed;
        this.distance = distance;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }
    public double getSpeed(){
        return speed;
    }
    public void setDistance(double distance){
        this.distance = distance;
    }
    public double getDistance(){
        return distance;
    }

    public void move(){
        double r = Math.random();
        distance += speed*r;
    }
    public void print(){
        System.out.println(String.format("%" + ((long)Math.floor(distance)+name.length()) + "s", name).replaceAll(" ", "."));
//        long dotsCount = (long)Math.floor(distance);
//        StringBuilder dots = new StringBuilder();
//        for(long i = 0; i < dotsCount; i++){
//            dots.append(".");
//        }
//
//        String s = dots + name;
//        System.out.println(s);
    }

    @Override
    public int compareTo(Horse o) {
        if (this.distance > o.distance) return 1;
        else if (this.distance < o.distance) return -1;
        else return 0;
    }

    @Override
    public String toString(){
        return name;
    }
}
