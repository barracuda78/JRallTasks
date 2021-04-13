package com.javarush.task.task29.task2909.human;

import java.util.List;

public class Teacher extends UniversityPerson {
    private int numberOfStudents;

    public Teacher(String name, int age, int numberOfStudents) {
        super(name, age);

        this.numberOfStudents = numberOfStudents;
    }


    public String getPosition(){
        return "Преподаватель";
    }
//    public List<Human> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Human> children) {
//        this.children = children;
//    }

    public void live() {
        teach();
    }

    public void teach() {
    }



//    public void printData() {
//        System.out.println("Преподаватель: " + name);
//    }
}