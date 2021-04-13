package com.javarush.task.task20.task2024;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
Прочитать в дополнительных материалах о сериализации графов.
Дан ориентированный плоский граф Solution, содержащий циклы и петли.

Пример, http://edu.nstu.ru/courses/saod/images/graph1.gif

Сериализовать Solution.
Все данные должны сохранить порядок следования.

Требования:
1. В классе Solution должно существовать поле edges.
2. В классе Solution должно существовать поле node.
3. Тип поля node должен быть int.
4. Класс Solution должен поддерживать интерфейс Serializable.
*/
public class Solution implements Serializable {
    int node;
    List<Solution> edges = new LinkedList<>();

    public static void main(String[] args) {
//        Solution s1 = new Solution();
//        s1.node = 2;
//
//        Solution s2 = new Solution();
//        s2.node = 4;
//
//        Solution solution = new Solution();
//        solution.node = 8;
//
//        solution.edges.add(s1);
//        solution.edges.add(s2);
//
//        File file = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2024\\1");
//        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
//
//            oos.writeObject(solution);
//
//            Solution solution2 = (Solution)ois.readObject();
//            System.out.println(solution2);
//        }
//        catch (IOException |ClassNotFoundException e){
//            e.printStackTrace();
//        }


    }

//    public String toString(){
//        return "node = " + node + "\n" + "Элементы edges = \n" +  edges;
//    }
}
