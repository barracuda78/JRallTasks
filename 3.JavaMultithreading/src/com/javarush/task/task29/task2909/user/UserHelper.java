package com.javarush.task.task29.task2909.user;

import java.util.concurrent.atomic.AtomicInteger;

public class UserHelper {
    private User userAnya = new User("Аня", "Смирнова", 10);
    private User userRoma = new User("Рома", "Виноградов", 30);

//    private boolean isManAnya = false;
//    private boolean isManRoma = true;

    public void printUsers() {
//        System.out.println("Имя: " + userAnya.getName());
//        System.out.println("Фамилия: " + userAnya.getSurname());
        userAnya.printInfo();
        userAnya.printAdditionalInfo();

//        System.out.println("Имя: " + userRoma.getName());
//        System.out.println("Фамилия: " + userRoma.getSurname());
        userRoma.printInfo();
        userRoma.printAdditionalInfo();
    }



//    private boolean ageLessThan16(User user) {
//        if (user.getAge() < 16) {
//            return true;
//        }
//        return false;
//    }


//    13.4. Расщепление переменной.
//    Переменная age в методе calculateAverageAge() используется для разных промежуточных значений. Перепиши метод без использования этой переменной.
    public int calculateAverageAge() {
        //int age = 28;
        User userUra = new User("Юра", "Карп", 28);

        return (userAnya.getAge() + userRoma.getAge() + userUra.getAge()) / 3;

        //return age;
    }

//    13.5. Удаление присваиваний параметрам. Перепиши метод calculateRate(), чтобы он не пытался менять входные параметры, а просто возвращал рассчитанное значение.
    public int calculateRate(AtomicInteger base, int age, boolean hasWork, boolean hasHouse) {
//        base.set(base.get() + age / 100);
//        base.set((int) (base.get() * (hasWork ? 1.1 : 0.9)));
//        base.set((int) (base.get() * (hasHouse ? 1.1 : 0.9)));
        int rate = (int)((base.get() + age/100) * (hasWork ? 1.1 : 0.9) * (hasHouse ? 1.1 : 0.9));
        return rate;
    }

    public String getBossName(User user) {
        return user.getBoss();
    }
}