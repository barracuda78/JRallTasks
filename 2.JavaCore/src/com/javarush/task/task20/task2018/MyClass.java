package com.javarush.task.task20.task2018;

public class MyClass {
    private int number;
    private String name = "default123";
    //    public MyClass(int number, String name) {
//        this.number = number;
//        this.name = name;
//    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setName(String name) {
        this.name = name;
    }
    private void printData(){
        System.out.println(number + name);
    }
}
