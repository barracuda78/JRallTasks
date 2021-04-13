package com.javarush.task.task20.task2013;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Externalizable Person
*/
public class Solution {
    public static class Person implements Externalizable{
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(){

        }

        public Person(String firstName, String lastName, int age)

        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        public List<Person> getChildren(){
            return children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String)in.readObject();
            lastName = (String)in.readObject();
            age = in.readInt();
            mother = (Person) in.readObject();
            father = (Person) in.readObject();
            children = (List) in.readObject();
        }

        @Override
        public String toString(){
            return "Имя: " + firstName + ". Фамилия: " +  lastName + ". Возраст: " + age + ".\nМать: " + mother.firstName + ". Отец: " + father.firstName + ".\nДети: " +  this.getChildren().get(0).firstName;
        }

    }

    public static void main(String[] args) {
        Person p = new Person("Andrey", "Zuzaev", 41);
        Person father = new Person("Vladimir", "Zuzaev", 70);
        Person mother = new Person("Lidia", "Zuzaeva", 69);
        Person daughter1 = new Person("Alisa", "Zuzaeva", 9);
        Person daughter2 = new Person("Olga", "Zuzaeva", 2);
        List<Person> children = new ArrayList<>();
        children.add(daughter1);
        children.add(daughter2);
        p.setChildren(children);
        p.setFather(father);
        p.setMother(mother);
//
//        Person pDeserialized = null;
//
//        File file = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2013\\1");
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
//            oos.writeObject(p);
//
//            pDeserialized = (Person)ois.readObject();
//
//            System.out.println(pDeserialized);
//        }
//        catch(IOException | ClassNotFoundException e){
//            e.printStackTrace();
//        }
    }
}
