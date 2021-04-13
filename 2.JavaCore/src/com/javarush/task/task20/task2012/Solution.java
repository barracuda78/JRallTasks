package com.javarush.task.task20.task2012;

import java.io.*;

/* 
OutputToConsole
Класс OutputToConsole должен сериализовываться с помощью интерфейса Externalizable.
Найди и исправь ошибку.

Требования:
1. Класс Solution.OutputToConsole должен поддерживать интерфейс Externalizable.
2. Класс OutputToConsole должен быть создан в классе Solution.
3. Класс OutputToConsole должен быть публичным.
4. Класс OutputToConsole должен быть статическим.
*/
public class Solution {
    public static String greeting = "Hello world";

    /**
     * OutputToConsole is an inner base class for improving your attentiveness.
     * An OutputToConsole object encapsulates the information needed
     * for displaying the [greeting] variable to the console.
     *
     * @author JavaRush
     */
    public static class OutputToConsole implements Serializable, Externalizable {
        private int counter;

        @Override
        public String toString(){
            return "" + this.counter;
        }
        /**
         * @param out A stream for externalization
         * @throws java.io.IOException
         */
        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeInt(counter);
        }

        /**
         * @param in A stream for de-externalization
         * @throws java.io.IOException
         * @throws ClassNotFoundException
         */
        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            counter = in.readInt();
        }

        /**
         * Class constructor that sets the private counter field.
         */
        public OutputToConsole(int counter) {
            this.counter = counter;
        }
        public OutputToConsole(){

        }
        /**
         * Prints greeting message to console counter times.
         */
        public void printMessage() {
            for (int i = 0; i < counter; i++) {
                System.out.println(greeting);
            }
        }
    }

    public static void main(String[] args) {
        OutputToConsole o = new OutputToConsole(3);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2012\\1")));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2012\\1")))){
            oos.writeObject(o);
            OutputToConsole o2 = (OutputToConsole) ois.readObject();
            o2.printMessage();
            System.out.println(o2);
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
