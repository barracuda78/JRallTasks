package com.javarush.task.task24.task2409;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Util {
    protected static Collection<Object[]> jeansArray = new LinkedList<>();

    static {
        jeansArray.add(new Object[]{1, Company.Levis, 34, 6, 150.0});
        jeansArray.add(new Object[]{2, Company.Denim, 35, 8, 154.0});
        jeansArray.add(new Object[]{3, Company.Colins, 32, 6, 120.0});
        jeansArray.add(new Object[]{4, Company.CalvinKleinJeans, 31, 8, 125.0});
    }

    public static List<Jeans> getAllJeans() {

        abstract class AbstractJeans implements Jeans{
            int id;
            int length;
            int size;
            double price;

            public abstract String getTM();

            @Override
            public int getId() {
                return id;
            }

            @Override
            public int getLength() {
                return length;
            }

            @Override
            public int getSize() {
                return size;
            }

            @Override
            public double getPrice() {
                return price;
            }


            public AbstractJeans(int id, int length, int size, double price){
                this.id = id; this.length = length; this.size = size; this.price = price;
            }

//1. В созданном абстрактном классе AbstractJeans implements Jeans создал private поля: int id,int length, int size, double price
//2. Провел инициализацию этих полей в конструкторе этого абстрактного класса;
//3. Реализовал имплементацию 4х геттеров полей абстрактного класса.
//4. Оверрайдил метод toString() абстрактного класса с возвращением склеенной строки,начинающейся с this.getClass().getSimpleName()


            @Override
            public String toString() {
                return getTM() + "{" +
                        "id=" + id +
                        ", length=" + length +
                        ", size=" + size +
                        ", price=" + price +
                        '}';
            }
        }
        ////////////////////////////////////////////////////////////////////
        class Levis extends AbstractJeans{
            public Levis(int id, int length, int size, double price) {
                super(id, length, size, price);
            }

            @Override
            public String getTM() {
                return Company.Levis.toString();
            }
        }
        /////////////////////////////////////////////////////////////////////
        class Denim extends AbstractJeans{
            public Denim(int id, int length, int size, double price) {
                super(id, length, size, price);
            }

            @Override
            public String getTM() {
                return Company.Denim.toString();
            }
        }
//2. В классе Util в методе getAllJeans добавь пропущенную часть java-кода:
//2.1) разберись в том, что уже есть в методе getAllJeans класса Util
//2.2) создай абстрактный class AbstractJeans от интерфейса Jeans с одним абстрактным методом, реализуй остальные методы
//2.3) создай классы Levis и Denim от AbstractJeans, реализуй оставшийся метод
//2.4) в классе AbstractJeans реализуй метод toString() (можешь воспользоваться Alt+Insert -> toString())
//2.5) метод toString класса AbstractJeans должен начинаться с имени подкласса, например, Levis{id=1, length=34, size=6, price=150.0}
        //add your code here

        List<Jeans> allJeans = new LinkedList<>();

        for (Object[] obj : getJeansArray()) {
            int id = (int) obj[0];
            final Company company = (Company) obj[1];
            int length = (int) obj[2];
            int size = (int) obj[3];
            double price = (double) obj[4];

            Jeans jeans = null;
            if (Company.Levis == company) {
                jeans = new Levis(id, length, size, price);
            } else if (Company.Denim == company) {
                jeans = new Denim(id, length, size, price);
            } else {
                jeans = new AbstractJeans(id, length, size, price) {
                    public String getTM() {
                        return company.fullName;
                    }
                };
            }
            allJeans.add(jeans);
        }





        return allJeans;
    }

    public static Collection<Object[]> getJeansArray() {
        return jeansArray;
    }

    static enum Company {
        Levis("Levi's"),
        Denim("Denim"),
        Colins("COLIN'S"),
        CalvinKleinJeans("Calvin Klein Jeans");

        final String fullName;

        Company(String name) {
            this.fullName = name;
        }
    }
}
