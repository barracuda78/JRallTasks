package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Human implements Alive{
    public class Size{
        public int height;
        public int weight;
    }



    private List<Human> children = new ArrayList<>();
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;
    protected Size size;



    //    public static final int FIRST = 1;
//    public static final int SECOND = 2;
//    public static final int THIRD = 3;
//    public static final int FOURTH = 4;
    private BloodGroup bloodGroup;
    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Human(String name, int age) {
        this.id = nextId;
        nextId++;
        this.name = name;
        this.age = age;
    }

    public String getPosition(){
        return "Человек";
    }

    public void printData() {
        System.out.println(getPosition() + ": " + name);
    }

    public List<Human> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Human human){
        children.add(human);
    }

    public void removeChild(Human human){
        for(int i = 0; i < children.size(); i++){
            if(children.get(i).id == human.id) children.remove(children.get(i));
        }
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void live() {
    }

    public int getId() {
        return id;
    }

}