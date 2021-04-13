package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File yourFile = File.createTempFile("your_file_name", null);
            File yourFile = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2002\\1");
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут

            //--------------инициализирую поле users для объекта javaRush
            User user01 = new User();
            user01.setFirstName("Andrey");
            user01.setLastName("Ruzaev");
            user01.setCountry(User.Country.OTHER);
            Date date01 = new Date(1978-1900, 7, 11);
            user01.setMale(true);
            user01.setBirthDate(date01);

            javaRush.users.add(user01);

            User user02 = new User();
            user02.setFirstName("Alisa");
            user02.setLastName("Ruzaeva");
            user02.setCountry(User.Country.RUSSIA);
            Date date02 = new Date(2010-1900, 7, 1);
            user02.setBirthDate(date02);
            user02.setMale(false);
            javaRush.users.add(user02);

            User user03 = new User();
            user03.setFirstName("Olya");
            user03.setLastName("Ruzaeva");
            user03.setCountry(User.Country.RUSSIA);
            Date date03 = new Date(2018-1900, 6, 15);
            user03.setBirthDate(date03);
            user03.setMale(false);
            javaRush.users.add(user03);
            //-------------------------------------------------------------


            javaRush.save(outputStream);
            outputStream.flush();
            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);


            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));


            System.out.println("пользователи из объекта javaRush");
            for(User user : javaRush.users){
                System.out.println(user.toString());
            }
            System.out.println("пользователи из объекта loadedObject");
            for(User user : javaRush.users){
                System.out.println(user.toString());
            }

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
        PrintWriter printWriter = new PrintWriter(outputStream);
        String isListNotEmpty = !users.isEmpty() ? "yes" : "no";
        printWriter.println(isListNotEmpty);                                                 //пишу в файл переменную isListNotEmpty
        printWriter.flush();

        if (isListNotEmpty.equals("yes")){
                                                                                            //пишем в файл число элементов в массиве:
            printWriter.println(users.size());
            printWriter.flush();
            //пишем для каждого элемента листа все его поля с проверками на пустоту.
            for(User user : users){
                printWriter.println(user.getFirstName());                                   //name
                printWriter.println(user.getLastName());                                       //lastname
                printWriter.println(user.getBirthDate().getTime());                             //date
                printWriter.println(user.isMale());                                             //sex
               // printWriter.println(user.getCountry());                                         //country
                printWriter.println(user.getCountry().toString());
                printWriter.flush();
            }
        }
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //пишу из файла переменную isListNotEmpty
            String isListNotEmpty = bufferedReader.readLine();                                                           //1 yes
            if(isListNotEmpty.equals("yes")){
                                                                                                                            // 2 читаю из файла число элементов в массиве:
                int usersSize = Integer.parseInt(bufferedReader.readLine());

                for (int i = 0; i < usersSize; i++) {
                    User user = new User();
                    String firstName = bufferedReader.readLine();
                    //System.out.println("Это firstName: " + firstName);
                    user.setFirstName(firstName);                                                               // 3 имя юзера прочитали и присвоили
                    String lastName = bufferedReader.readLine();
                    //System.out.println("Это lastName: " + lastName);
                    user.setLastName(lastName);                                                                //  4 фамилию
                    String d = bufferedReader.readLine();
                    //System.out.println("это date: " + d); //---------------------------------------------
                    user.setBirthDate(new Date(Long.parseLong(d)));                                                             //  5  дату рождения в миллисекундах
                    boolean isMale = bufferedReader.readLine().equals("true") ? true : false;                                    // 6 true false
                    //System.out.println("Это пол: " + isMale);
                    user.setMale(isMale);
                    //-------------------------------
                    String cntr = bufferedReader.readLine();
                    User.Country country;
                    if(cntr.equals("Russia")){                                                                 //7 country
                        country = User.Country.RUSSIA;
                    }
                    else if(cntr.equals("Ukraine")){
                        country = User.Country.UKRAINE;
                    }
                    else{
                        country = User.Country.OTHER;
                    }
                    //user.setCountry(country);
                    user.setCountry(User.Country.valueOf(cntr));
                    //System.out.println("Это страна:" + country);


                    users.add(user);

                }
            }
            bufferedReader.close();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
