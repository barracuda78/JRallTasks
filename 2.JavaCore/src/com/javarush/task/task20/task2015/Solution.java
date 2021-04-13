package com.javarush.task.task20.task2015;

import java.io.*;

/* Переопределение сериализации
Сделайте так, чтобы после десериализации нить runner продолжила работать.
Ключевые слова объекта runner менять нельзя.
Hint/Подсказка:
Конструктор не вызывается при десериализации, только инициализируются все поля.
Требования:
1. Класс Solution должен поддерживать интерфейс Serializable.
2. Класс Solution должен поддерживать интерфейс Runnable.
3. Поле runner в классе Solution должно быть объявлено с модификатором transient.
4. В методе readObject должен быть создан новый объект типа Thread с текущим объектом в качестве параметра.
5. В методе readObject должен быть вызван метод start у нового объекта типа Thread.*/

public class Solution implements Serializable, Runnable {
    transient private Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, doesn't matter what
        System.out.println("thread is running...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Переопределяем сериализацию.
     * Для этого необходимо объявить методы:
     * private void writeObject(ObjectOutputStream out) throws IOException
     * private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     * Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        Thread thread = new Thread(this);
        thread.start();
    }

    public static void main(String[] args) {
        Solution solution = new Solution(18);
        File file = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2015\\1");

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){

            oos.writeObject(solution);
            System.out.println("Сериализация объекта solution прошла успешно.");

            //Solution solution2 = (Solution)ois.readObject();
            ois.readObject();
        }
        catch(IOException |ClassNotFoundException f){
            f.printStackTrace();
        }
    }
}
