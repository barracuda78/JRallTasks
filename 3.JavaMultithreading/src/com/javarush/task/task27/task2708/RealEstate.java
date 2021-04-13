package com.javarush.task.task27.task2708;

import java.util.HashSet;
import java.util.Set;

public class RealEstate {

    private final Set<Apartment> allApartments;
    private final Set<Apartment> activeApartments;

    public RealEstate() {
        allApartments = new HashSet();
        activeApartments = new HashSet();

        //add some data
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
        allApartments.add(new Apartment(this));
    }

    public Set<Apartment> getAllApartments() {
        return allApartments;
    }

    public void up(Apartment apartment) {
        activeApartments.add(apartment);
    }

//    5. Метод revalidate класса RealEstate должен содержать один synchronized блок.
//6. В synchronized блоке метода revalidate класса RealEstate должен содержаться вызов метода revalidate на объекте apartment с параметром randomValue.
    public void revalidate() {
        activeApartments.clear();
        for (Apartment apartment : allApartments) {
            boolean randomValue = Math.random() * 2 % 2 == 0;
            synchronized (this) {
                apartment.revalidate(randomValue);
            }
        }
    }
}
