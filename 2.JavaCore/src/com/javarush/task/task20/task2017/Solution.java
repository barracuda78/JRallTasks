package com.javarush.task.task20.task2017;

import java.io.*;

/* Десериализация
На вход подается поток, в который записан сериализованный объект класса A либо класса B.
Десериализуй объект в методе getOriginalObject так, чтобы в случае возникновения исключения было выведено сообщение на экран и возвращен null.
Реализуй интерфейс Serializable где необходимо.
Требования:
1. Класс B должен быть потомком класса A.
2. Класс A должен поддерживать интерфейс Serializable.
3. Класс B не должен явно поддерживать интерфейс Serializable.
4. Метод getOriginalObject должен возвращать объект типа A полученный из потока ObjectInputStream.
5. Метод getOriginalObject должен возвращать null, если при попытке десериализации не был получен объект типа A.
6. Метод getOriginalObject должен возвращать null, если при попытке десериализации возникло исключение.*/
public class Solution implements Serializable{
    public A getOriginalObject(ObjectInputStream objectStream) {
        try {
            A a = null;
            Object o = objectStream.readObject();
            if(o instanceof A) {
                a = (A)o;
                return a;
            }
            else {
                System.out.println("это не А.");
                return null;
            }
        }
        catch(Exception e){
            //e.printStackTrace();
            System.out.println("Ошибка десериализации.");
            return null;
        }
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {
        //A aObject = new Solution().new A();
        B bObject = new Solution().new B();

        //File file1 = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2017\\1");
        File file2 = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2017\\B");

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file2));
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file2))){

            oos.writeObject(bObject);

            //A aNew = (A)ois.readObject();
            //B bNew = (B)ois.readObject();
            A aNew = new Solution().getOriginalObject(ois);
            //A aNew = new Solution().getOriginalObject(ois);
            System.out.println(aNew);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
