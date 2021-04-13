package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Test {
    public static List<Person> allPeople = new ArrayList<Person>();
    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {

        String bd = "15/04/1990";
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        //Date docDate = format1.parse(bd);
        //System.out.println(docDate);

        int id = 1;
        System.out.println("размер до удаление: " + allPeople.size());
        //id = Integer.parseInt(args[1]);
        //allPeople.remove(id);
        Person nullPerson = allPeople.get(id);
        nullPerson.setName(null);
        nullPerson.setSex(null);
        nullPerson.setBirthDate(null);
        allPeople.set(id, nullPerson);
        System.out.println("размер после удаление: " + allPeople.size());

/*        System.out.println();
        System.out.println("Выводим персон с id = " + id);
        Person person = allPeople.get(id);
        System.out.print(person.getName() + " ");
        Sex personSex = person.getSex();
        if (personSex.equals(Sex.MALE)){
            System.out.print("м ");
        }
        else if(personSex.equals(Sex.FEMALE)){
            System.out.print("ж ");
        }
        SimpleDateFormat format3 = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        System.out.print(format3.format(person.getBirthDate()));*/
    }
}
