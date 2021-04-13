package com.javarush.task.task29.task2909.user;

public class User {
//    14.1. Перемещение поля. Замени поля isManAnya и isManRoma полем man в нужном классе.
//Добавь сеттер и геттер для нового поля (при выборе имен методов учти тип поля).

    private boolean man;
    private String name;
    private String surname;
    private int age;
    private Address address;
    private Work work;

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    //Ты не вызвал в методе getBoss() класса User метод getBoss() у объекта типа Work.
    public String getBoss(){
        return work.getBoss();
    }


//    В классе User необходимо переписать методы: getAddress, getCountry, setCountry, getCity, setCity. И нужно добавить в класс приватное поле Address address.

    public String getAddress() {
        return address.getCountry() + " " + address.getCity() + " " + address.getHouse();
    }

    public String getCountry(){
        return address.getCountry();
    }

    public void setCountry(String country){
        this.address.setCountry(country);
    }

    public String getCity(){
        return address.getCity();
    }

    public void setCity(String city){
        this.address.setCity(city);
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }

    public void printInfo(){
        System.out.println("Имя: " + name);
        System.out.println("Фамилия: " + surname);
    }

    public void printAdditionalInfo() {
        if (getAge() < 16)
            System.out.println("Пользователь моложе 16 лет");
        else
            System.out.println("Пользователь старше 16 лет");
    }
}