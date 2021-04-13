package com.javarush.task.task24.task2413;


import java.util.ArrayList;
import java.util.List;

public class Test extends Thread{

public interface Animal{
    void say();
}

public class Cat implements Animal{
    @Override
    public void say(){
        System.out.println("Мяу!");
    }
}

public class Dog implements Animal{
    @Override
    public void say(){
        System.out.println("Гав!");
    }
}

public class Frog implements Animal{
    @Override
    public void say(){
        System.out.println("Ква-Ква!");
    }
}

public interface OurFactory{
    Animal createAnimal(int i);
}

public class AnimalFactory implements OurFactory{

    @Override
    public Animal createAnimal(int i) {
        if (i%2==0){
            return new Frog();
        }
        else if(i%3==0){
            return new Cat();
        }
        else {
            return new Dog();
        }
    }
}

    public static void main(String[] args) {
        Test test = new Test();
        AnimalFactory animalFactory = test.new AnimalFactory();
        List<Animal> listOfAnimals = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            listOfAnimals.add(animalFactory.createAnimal(i));
        }

        for(Animal animal : listOfAnimals){
            animal.say();
        }
    }

}
