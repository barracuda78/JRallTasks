package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends UniversityPerson {
    //private List<Human> children = new ArrayList<>();
    private int course;
    private double averageGrade;
    private Date beginningOfSession;
    private Date endOfSession;

    public Student(String name, int age, double averageGrade) {
        super(name, age);

        this.averageGrade = averageGrade;
    }

    public String getPosition(){
        return "Студент";
    }

//    public List<Human> getChildren() {
//        return children;
//    }
//
//    public void setChildren(List<Human> children) {
//        this.children = children;
//    }

    public void live() {
        learn();
    }

    public void learn() {
    }

    public int getCourse() {
        return course;
    }

//9.1. Самоинкапсуляция поля. Перепиши метод incAverageGrade() используя сеттер и геттер для доступа к averageGrade.

    public void incAverageGrade(double delta){
        setAverageGrade(getAverageGrade() + delta);
    }





    public void setCourse(int course){
        this.course = course;
    }

    public void setBeginningOfSession(Date date) {
        beginningOfSession = date;
    }
    public void setEndOfSession(Date date) {
        endOfSession = date;
    }
    public void setAverageGrade(double averageGrade){
        this.averageGrade = averageGrade;
    }
    public double getAverageGrade() {
        return averageGrade;
    }
}