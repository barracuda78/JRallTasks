package com.javarush.task.task19.task1903;

/* Адаптация нескольких интерфейсов
Адаптируй IncomeData к Customer и Contact.
Классом-адаптером является IncomeDataAdapter.
Инициализируйте countries перед началом выполнения программы. Соответствие кода страны и названия:
UA Ukraine
RU Russia
CA Canada
Дополнить телефонный номер нулями до 10 цифр при необходимости (смотри примеры).
Обратите внимание на формат вывода фамилии и имени человека.


Требования:
1. Класс Solution должен содержать public static поле countries типа Map<String, String>.
2. В статическом блоке класса Solution инициализируй поле countries тестовыми данными согласно заданию.
3. Класс IncomeDataAdapter должен реализовывать интерфейсы Customer и Contact.
4. Класс IncomeDataAdapter должен содержать приватное поле data типа IncomeData.
5. Класс IncomeDataAdapter должен содержать конструктор с параметром IncomeData.
6. В классе IncomeDataAdapter реализуй методы интерфейсов Customer и Contact используя подсказки в виде комментариев в интерфейсах.*/

import java.sql.SQLOutput;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();
    static{
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }


    public static void main(String[] args) {

    }

    public static class IncomeDataAdapter implements Customer, Contact{
        private IncomeData data;

        public IncomeDataAdapter(IncomeData incomeData){
            this.data = incomeData;
        }

        @Override ////For example: JavaRush Ltd.
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            String countryName = "";
            String countryCode = data.getCountryCode();
            for(Map.Entry<String, String> pair : countries.entrySet()){
                String key = pair.getKey();
                String value = pair.getValue();
                if(countryCode.equals(key)) countryName = value;
            }
            return countryName;
        }

        @Override//Обратите внимание на формат вывода фамилии и имени человека.
        public String getName() {
            String firstName = data.getContactFirstName();
            String lastName = data.getContactLastName();

            //For example: Ivanov, Ivan
            return String.format("%s" + ", " + "%s", lastName, firstName);
        }

        @Override//Дополнить телефонный номер нулями до 10 цифр при необходимости (смотри примеры).
        public String getPhoneNumber() {
            String codeS = "" + data.getCountryPhoneCode();  //For example: 38
            int code = Integer.parseInt(codeS);
            String phoneW = "" + data.getPhoneNumber();      //For example: 501234567
            if(phoneW.length() > 10) phoneW = phoneW.substring(0, 10);
            //тут к phone2 добавить нули до 10-ти.
            while(phoneW.length() < 10){
                phoneW = "0" + phoneW;
            }

            String phone = phoneW;
            String cityCode = phone.substring(2, 5);
            String phone1 = phone.substring(5, 8);
            String phone2 = phone.substring(8, 10);
            String phone3 = phone.substring(10);

            //For example: +38(050)123-45-67
            //return String.format("+%s(%s)%s-%s-%s", codeS, cityCode, phone1, phone2, phone3);

            String phoneNumber = "+" + String.format("%d(%2$s)%3$s-%4$s-%5$s", code,
                    phone.substring(0,3), phone.substring(3,6),
                    phone.substring(6,8), phone.substring(8,10));

            return phoneNumber;

        }
    }


    public static interface IncomeData {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        int getCountryPhoneCode();      //For example: 38

        int getPhoneNumber();           //For example: 501234567
    }

    public static interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public static interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example: +38(050)123-45-67
    }
}