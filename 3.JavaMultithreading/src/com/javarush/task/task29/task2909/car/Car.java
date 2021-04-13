package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption; //??liter/100km
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public static Car create(int type, int numberOfPassengers){
        switch (type){
            case 0: return new Truck(numberOfPassengers);
            case 1: return new Sedan(numberOfPassengers);
            case 2: return new Cabriolet(numberOfPassengers);
            default: break;
        }
        return null;
    }
//    12.1.1. Добавь внутренний метод, сообщающий, могут ли быть перевезены пассажиры boolean canPassengersBeTransferred() в класс Car.
//Метод должен возвращать true, если водитель доступен isDriverAvailable и есть топливо fuel.
    private boolean canPassengersBeTransferred(){
        return (fuel > 0 && driverAvailable);
    }



//11.1. Замена кода ошибки исключением. Перепиши метод заправиться fill(double numberOfLiters), чтобы он в случае ошибки кидал исключение Exception.
    public void fill(double numberOfLiters) throws Exception{
        if (numberOfLiters < 0)
            throw new Exception();
        fuel += numberOfLiters;
    }


//11.2. Разбиение условного оператора.
//11.2.1. Добавь и реализуй метод в классе Car, определяющий относится ли переданная дата к лету: boolean isSummer(Date date, Date summerStart, Date summerEnd).
    public boolean isSummer(Date date, Date summerStart, Date summerEnd){
        if(date.before(summerStart) || date.after(summerEnd)) return false;
        return true;
    }

    //11.2.2. Добавь и реализуй метод, рассчитывающий расход топлива зимой: double getWinterConsumption(int length).
    public double getWinterConsumption(int length){
        //liter / km
        //to return liters (needed to ride length.
        double liters = winterFuelConsumption * length + winterWarmingUp;
        return liters;
    }

    //11.2.3. Добавь и реализуй метод, рассчитывающий расход топлива летом: double getSummerConsumption(int length).
    public double getSummerConsumption(int length){
        double liters = summerFuelConsumption * length;
        return liters;
    }

    //11.2.4. Перепиши метод getTripConsumption(), используя новые методы.
    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (!isSummer(date, SummerStart, SummerEnd)) {
            consumption = getWinterConsumption(length);
        } else {
            consumption = getSummerConsumption(length);
        }
        return consumption;
    }

//    12.1.2. Перепиши метод getNumberOfPassengersCanBeTransferred(), объединив условные операторы (используй метод canPassengersBeTransferred()).
    public int getNumberOfPassengersCanBeTransferred() {
        if(!canPassengersBeTransferred()) return 0;
        return numberOfPassengers;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }


    //12.2. Объединение дублирующихся фрагментов в условных операторах. Перепиши метод startMoving(), чтобы в нем не было повторяющихся вызовов функций.
    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();

        }
        fastenDriverBelt();
    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

//    12.3. Замена магического числа символьной константой. Замени магические числа в методе getMaxSpeed() на константные переменные метода:
//MAX_TRUCK_SPEED, MAX_SEDAN_SPEED и MAX_CABRIOLET_SPEED.

    public abstract int getMaxSpeed();
//    {
//        final int MAX_TRUCK_SPEED = 80;
//        final int MAX_SEDAN_SPEED = 120;
//        final int MAX_CABRIOLET_SPEED = 90;
//        if (type == TRUCK)
//            return MAX_TRUCK_SPEED;
//        if (type == SEDAN)
//            return MAX_SEDAN_SPEED;
//        return MAX_CABRIOLET_SPEED;
//    }
}