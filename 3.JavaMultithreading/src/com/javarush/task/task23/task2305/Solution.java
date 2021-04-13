package com.javarush.task.task23.task2305;

/* 
Inner
Реализовать метод getTwoSolutions, который должен возвращать массив из 2-х экземпляров класса Solution.
Для каждого экземпляра класса Solution инициализировать поле innerClasses двумя значениями.
Инициализация всех данных должна происходить только в методе getTwoSolutions.

Требования:
1. В классе Solution должен быть реализован метод getTwoSolutions.
2. Метод getTwoSolutions должен быть статическим.
3. Метод getTwoSolutions должен быть публичным.
4. Метод getTwoSolutions должен возвращать массив типа Solution заполненный согласно заданию.
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {

    }

    public static Solution[] getTwoSolutions() {
        Solution solution = new Solution();
        Solution solution2 = new Solution();
        InnerClass ic1 = solution.new InnerClass();
        InnerClass ic2 = solution.new InnerClass();
        solution.innerClasses[0] = ic1;
        solution.innerClasses[1] = ic2;
        solution2.innerClasses[0] = ic1;
        solution2.innerClasses[1] = ic2;

        Solution[] sArray = new Solution[2];
        sArray[0] = solution;
        sArray[1] = solution2;

        return sArray;
    }

    public static void main(String[] args) {

    }
}
