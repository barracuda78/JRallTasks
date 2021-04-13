package com.javarush.task.task26.task2609;



public class Test {
    public static void main(String[] args) {
        System.out.println(new AstronomToAcademHours().astrToAcad(54));
    }

}

class AstronomToAcademHours{

    public double astrToAcad(double astonomicHours){
        double academicHours = 0;
        double koeff = 60.0/45.0;
        academicHours = koeff*astonomicHours;
        return academicHours;
    }
}


