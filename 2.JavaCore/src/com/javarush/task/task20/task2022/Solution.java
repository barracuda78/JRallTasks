package com.javarush.task.task20.task2022;

import java.io.*;

/*
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient private FileOutputStream stream;
    private String fileName;

    //5. В конструкторе класса Solution поле stream должно быть инициализировано новым объектом типа FileOutputStream с параметром(fileName).
    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.fileName = fileName;
    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\r\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        //out.close();
    }
//4. В методе readObject(ObjectInputStream in) поле stream должно быть инициализировано новым объектом типа FileOutputStream с параметрами(fileName, true).
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.stream = new FileOutputStream(fileName, true);
        //in.close();
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) {
        try(Solution solution = new Solution("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2022\\1");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2022\\1")));
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2022\\1"))){
            solution.writeObject("12345");
            oos.writeObject(solution);

            Solution solution2 = (Solution)ois.readObject();
            solution2.writeObject("12345");

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
