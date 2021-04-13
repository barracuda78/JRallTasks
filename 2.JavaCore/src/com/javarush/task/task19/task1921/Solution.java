package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Хуан Хуанович
//sArray.length = 6
//   0     1     2      3  4   5
//Иванов Иван Иванович 31 12 1987
//Вася 15 5 2013
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        try {
            //читаем имя файла
            String fileName = args[0];

            //читаем строки в list
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            while(bufferedReader.ready()){
                String s = bufferedReader.readLine();
                String[] sArray = s.split(" ");
                int year = Integer.parseInt(sArray[sArray.length - 1]);
                int month = Integer.parseInt(sArray[sArray.length - 2]);
                int day = Integer.parseInt(sArray[sArray.length - 3]);
                Date dateOfBirth = new Date(year - 1900, month - 1, day);

                String name = "";
                for (int i = 0; i <sArray.length - 3 ; i++) {
                    name += (sArray[i] + " ");
                }
                name = name.substring(0, name.length()-1);

                Person person = new Person(name, dateOfBirth);
                PEOPLE.add(person);
            }
            bufferedReader.close();



        }
        catch(IOException i){
            i.printStackTrace();
        }

    }

}
