package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-u id name sex bd
-d id
-i id

Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-u - обновляет данные человека с данным id
-d - производит логическое удаление человека с id, заменяет все его данные на null
-i - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)

id соответствует индексу в списке.
Все люди должны храниться в allPeople.
Используйте Locale.ENGLISH в качестве второго параметра для SimpleDateFormat.

Пример параметров:
-c Миронов м 15/04/1990

Пример вывода для параметра -і:
Миронов м 15-Apr-1990

Требования:
1. Класс Solution должен содержать public static поле allPeople типа List<Person>.
2. Класс Solution должен содержать статический блок, в котором добавляются два человека в список allPeople.
3. При запуске программы с параметром -с программа должна добавлять человека с заданными параметрами в конец списка allPeople, и выводить id (index) на экран.
4. При запуске программы с параметром -u программа должна обновлять данные человека с заданным id в списке allPeople.
5. При запуске программы с параметром -d программа должна логически удалять человека с заданным id в списке allPeople.
6. При запуске программы с параметром -i программа должна выводить на экран данные о человеке с заданным id по формату указанному в задании.
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        String crud = args[0];
        String name;
        String sex;
        String bd;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        if(crud.equals("-c")){
            name = args[1];
            sex = args[2];
            bd = args[3];
            Date date = null;
            try {
                date = format.parse(bd);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(sex.equals("м")){
                allPeople.add(Person.createMale(name, date));
                System.out.println(allPeople.size()-1);

            }
            else if(sex.equals("ж")){
                allPeople.add(Person.createFemale(name, date));
                System.out.println(allPeople.size()-1);
            }
        }
        else if(crud.equals("-u")){
            int id = Integer.parseInt(args[1]);
            name = args[2];
            sex = args[3];
            bd = args[4];
            Date date = null;
            try {
                date = format.parse(bd);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Person person = allPeople.get(id);
            person.setName(name);
            if(sex.equals(Sex.MALE)) person.setSex(Sex.MALE);
            else person.setSex(Sex.FEMALE);
            person.setBirthDate(date);
            allPeople.set(id, person);
        }

        else if(crud.equals("-d")){
            int id = Integer.parseInt(args[1]);
            Person nullPerson = allPeople.get(id);
            nullPerson.setName(null);
            nullPerson.setSex(null);
            nullPerson.setBirthDate(null);
            allPeople.set(id, nullPerson);

//            allPeople.get(Integer.parseInt(args[1])).setName(null);
//            allPeople.get(Integer.parseInt(args[1])).setSex(null);
//            allPeople.get(Integer.parseInt(args[1])).setBirthDate(null);

        }
        else if(crud.equals("-i")) {
            int id = Integer.parseInt(args[1]);
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

        }

    }
}
