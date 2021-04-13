package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //-----------------------
        //String allArgs = "-c Peter м 15/04/1990 Jannet ж 11/08/1977";
        //-u id1 name1 sex1 bd1 id2 name2 sex2 bd2
        //-d id1 id2 id3 id4 ...
        //String allArgs = "-u 0 Vasia м 11/11/1111 1 Petia м 22/22/2010";
        //String allArgs = "-d 0 1";
        //-i id1 id2 id3 id4 ...
        //String allArgs = "-i 0 1";
        //String nullArgs = allArgs.substring(0, allArgs.indexOf(' '));
        //String[] args2 = allArgs.split(" ");
        //-----------------------


        String crud = args[0];
        String name;
        String sex;
        String bd;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        switch(args[0])   {
            case "-c": {
                synchronized (allPeople){
                    for (int i = 0; i < args.length-3; i=i+3) {
                        name = args[i+1];
                        sex = args[i+2];
                        bd = args[i+3];
                        Date date = null;
                        try {
                            date = format.parse(bd);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if(sex.equals("м")){
                            allPeople.add(Person.createMale(name, date));
                        }
                        else if(sex.equals("ж")){
                            allPeople.add(Person.createFemale(name, date));
                        }
                        System.out.println(allPeople.size()-1);
                    }
                }
                break;
            }
            case "-u": {
                synchronized (allPeople){
                    for (int i = 0; i < args.length-1; i=i+4) {
                        int id = 0;
                        try {
                            id = Integer.parseInt(args[i + 1]);
                        }
                        catch(NumberFormatException n){

                        }
                        name = args[i+2];
                        sex = args[i+3];
                        bd = args[i+4];
                        Date date = null;

                        try {
                            date = format.parse(bd);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Person person = allPeople.get(id);
                        person.setName(name);
                        if(sex.equals("м")) person.setSex(Sex.MALE);
                        else person.setSex(Sex.FEMALE);
                        person.setBirthDate(date);
                        allPeople.set(id, person);
                    }
                }
                break;
            }
            case "-d": {
                synchronized (allPeople){
                    for (int i = 0; i < args.length-1; i++) {
                        int id = 0;
                        try {
                            id = Integer.parseInt(args[i + 1]);
                        }
                        catch(NumberFormatException n){

                        }
                        Person nullPerson = allPeople.get(id);
                        nullPerson.setName(null);
                        nullPerson.setSex(null);
                        nullPerson.setBirthDate(null);
                        allPeople.set(id, nullPerson);
                    }
                }
                break;
            }
            case "-i": {
                synchronized (allPeople){
                    for (int i = 0; i < args.length-1; i++) {
                        int id = 0;
                        try {
                            id = Integer.parseInt(args[i + 1]);
                        }
                        catch(NumberFormatException n){

                        }
                        Person person = allPeople.get(id);

                        System.out.print(person.getName() + " ");
                        Sex personSex = person.getSex();
                        if (personSex.equals(Sex.MALE)){
                            System.out.print("м ");
                        }
                        else if(personSex.equals(Sex.FEMALE)){
                            System.out.print("ж ");
                        }
                        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                        System.out.print(format2.format(person.getBirthDate()));
                        System.out.println();
                    }
                }
                break;
            }
        }

    }
}
