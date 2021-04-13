package com.javarush.task.task20.task2011;
import java.io.*;

/*
Externalizable для апартаментов
Реализуй интерфейс Externalizable в классе Apartment.
Требования:
1. Класс Solution.Apartment должен поддерживать интерфейс Externalizable.
2. В классе Solution.Apartment должен быть реализован метод writeExternal с одним параметром типа ObjectOutput.
3. В классе Solution.Apartment должен быть реализован метод readExternal с одним параметром типа ObjectInput.
4. В методе writeExternal, на полученном в качестве параметра объекте типа ObjectOutput должен быть вызван метод writeObject с параметром address.
5. В методе writeExternal, на полученном в качестве параметра объекте типа ObjectOutput должен быть вызван метод writeInt с параметром year.
6. Метод readExternal должен корректно восстанавливать из ObjectInput значение поля address.
7. Метод readExternal должен корректно восстанавливать из ObjectInput значение поля year.
*/
public class Solution {

    public static class Apartment implements Externalizable{

        private String address;
        private int year;

        /**
         * Mandatory public no-arg constructor.
         */
        public Apartment() {
            super();
        }

        public Apartment(String addr, int y) {
            address = addr;
            year = y;
        }

        public void writeExternal(ObjectOutput out){
            try {
                out.writeObject(this.address);
                out.writeInt(year);
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        public void readExternal(ObjectInput in){
            try {
                address = (String) in.readObject();
                year = in.readInt();
            }
            catch(IOException | ClassNotFoundException io){
                io.printStackTrace();
            }
        }

        /**
         * Prints out the fields used for testing!
         */
        public String toString() {
            return ("Address: " + address + "\n" + "Year: " + year);
        }
    }

    public static void main(String[] args) {
//        Apartment apartment = new Apartment("SPb", 2008);
//        File file01 = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2011\\1");
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file01));
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file01))){
//            oos.writeObject(apartment);
//            Apartment apartment1 = (Apartment)ois.readObject();
//            System.out.println(apartment1);
//        }
//        catch(IOException | ClassNotFoundException e){
//            e.printStackTrace();
//        }


    }
}
