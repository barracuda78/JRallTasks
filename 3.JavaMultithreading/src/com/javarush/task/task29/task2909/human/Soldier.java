package com.javarush.task.task29.task2909.human;

public class Soldier extends Human{
    private BloodGroup bloodGroup;
    public Soldier(String name, int age){
        super(name, age);
    }

    public void fight() {
    }

    public void live() {
            fight();
    }

    public void setBloodGroup(int code) {
        switch(code){
            case 1: {
                setBloodGroup(BloodGroup.first());
                break;
            }
            case 2: {
                setBloodGroup(BloodGroup.second());
                break;
            }
            case 3: {
                setBloodGroup(BloodGroup.third());
                break;
            }
            case 4: {
                setBloodGroup(BloodGroup.fourth());
                break;
            }
        }

    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void printSize() {
        System.out.println("Рост: " + size.height + " Вес: " + size.weight);
    }
}
