package com.javarush.task.task20.task2016;

import java.io.*;

/*
Минимум изменений
Используя минимум изменений кода сделайте так, чтобы сериализация класса C стала возможной.

Требования:
1. В классе Solution должен существовать класс A.
2. В классе Solution должен существовать класс B.
3. В классе Solution должен существовать класс C.
4. Класс B должен быть потомком класса A.
5. Класс C должен быть потомком класса B.
6. Класс A должен поддерживать интерфейс Serializable.
7. Класс B не должен явно поддерживать интерфейс Serializable.
8. Класс C не должен явно поддерживать интерфейс Serializable.
*/
public class Solution implements Serializable{
    public class A implements Serializable {
        String name = "A";

        public A(String name) {
            this.name += name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public class B extends A {
        String name = "B";

        public B(String name) {
            super(name);
            this.name += name;
        }
    }

    public class C extends B {
        String name = "C";

        public C(String name) {
            super(name);
            this.name = name;
        }

        public String toString(){
            return  this.name;
        }
    }

    public static void main(String[] args) {
        C cClass = new Solution().new C("это класс C");

        File file = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2016\\1");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
            oos.writeObject(cClass);
            C newClassC = (C)ois.readObject();
            System.out.println(newClassC);
        }
        catch(IOException | ClassNotFoundException i){
            i.printStackTrace();
        }

    }
}
