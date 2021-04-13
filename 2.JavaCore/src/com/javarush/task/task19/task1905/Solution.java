package com.javarush.task.task19.task1905;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Закрепляем адаптер
Адаптировать Customer и Contact к RowItem.
Классом-адаптером является DataAdapter.

Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada

Требования:
1. Класс Solution должен содержать public static поле countries типа Map<String, String>.
2. В статическом блоке класса Solution инициализируй поле countries тестовыми данными согласно заданию.
3. Класс Solution должен содержать интерфейс RowItem.
4. Класс Solution должен содержать интерфейс Contact.
5. Класс Solution должен содержать интерфейс Customer.
6. Класс DataAdapter должен реализовывать интерфейс RowItem.
7. Класс DataAdapter должен содержать два приватных поля: customer типа Customer и contact Contact.
8. Класс DataAdapter должен содержать конструктор с параметрами (Customer customer, Contact contact), который инициализирует поля contact и customer.
9. В классе DataAdapter реализуй методы интерфейса RowItem используя подсказки в виде комментариев в интерфейсах.*/

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static{
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {

    }

    public static class DataAdapter implements RowItem{
        private Customer customer;
        private Contact contact;
        public DataAdapter(Customer customer, Contact contact) {
            this.contact = contact;
            this.customer = customer;
        }

        @Override
        public String getCountryCode() { //For example: UA
            String countryCode = null;
            String countryName = customer.getCountryName();   //For example: Ukraine
            for(Map.Entry<String, String> pair : countries.entrySet()){
                String key = pair.getKey();
                String value = pair.getValue();
                if(countryName.equals(value)) countryCode = key;
            }
            return countryCode;
        }

        @Override
        public String getCompany() {    //For example: JavaRush Ltd.
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {   //For example: Ivan
            String lastnameFirstname =  contact.getName();               ////For example: Ivanov, Ivan
            String firstName = lastnameFirstname.substring(lastnameFirstname.indexOf(" ") + 1);
            return firstName;
        }

        @Override
        public String getContactLastName() {   //For example: Ivanov

            String lastnameFirstname =  contact.getName();               ////For example: Ivanov, Ivan
            String lastName = lastnameFirstname.substring(0, lastnameFirstname.indexOf(","));
            return lastName;
        }

        //Метод getDialString() должен вернуть строку состоящую из надписи
        // callto://+  и телефонного номера из которого убраны все символы кроме цифр(смотри примеры).
        // Номер телефона нужно взять из getPhoneNumber() объекта сontact.
        @Override
        public String getDialString() {    //For example: callto://+380501234567
            String phoneNumber = contact.getPhoneNumber();  //+38(050)123-45-67 or +3(805)0123-4567 or +380(50)123-4567 or ...
            phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
            return "callto://+" + phoneNumber;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        String getDialString();         //For example: callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example:
    }
}