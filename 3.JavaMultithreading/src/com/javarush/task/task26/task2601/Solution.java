package com.javarush.task.task26.task2601;

import java.util.*;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
        //при валидации метод мейн должен быть пустым.
//        Integer[] array = {13, 8, 15, 5, 17};
//        Solution.sort(array);
//        System.out.println(Arrays.toString(array));
    }

    public static Integer[] sort(Integer[] array) {

        class Medianator implements Comparable<Medianator>{
            int offset;
            int index;
            int value;

            public Medianator(int offset, int value){
                this.offset = offset;
                this.value = value;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public void setIndex(int index){
                this.index = index;
            }

            public int getOffset() {
                return offset;
            }

            public int getIndex() {
                return index;
            }

            public int getValue() {
                return value;
            }

            @Override
            public int compareTo(Medianator o) {
                if(offset > o.offset) return 1;
                else if(offset < o.offset) return -1;
                else if(value > o.value) return 1;
                else if(value < o.value) return -1;
                else return 0;
            }
            @Override
            public String toString(){
                return "параметры медианатора: оффсет" + offset + " , значение: " + value + " , индекс: " + index;
            }
        }

        //1:
        //объявить переменную - медиану.
        int mediana;

        //проверка массива на нечетность.
        //если нечетный:
        if(array.length % 2 != 0){
            Integer[] testArray = array;
            Arrays.sort(testArray);
            mediana = testArray[testArray.length/2];
        }
        //если четный:
        else{
            Integer[] testArray = array;
            Arrays.sort(testArray);
            int mediana1 = testArray[testArray.length/2];
            int mediana2 = testArray[testArray.length/2 - 1];
            mediana = (mediana1 + mediana2)/2;
        }

        //2.1.
        List<Medianator> listOfMedianators = new ArrayList<>();

        for(int i = 0; i < array.length; i++){
            int keyOffset = mediana - array[i];
            if (keyOffset < 0)
                keyOffset *= -1; //получение расстояния до медианы по модулю.
            int value = array[i];
            Medianator medianator = new Medianator(keyOffset, value);
            listOfMedianators.add(medianator);
        }

        //отсортировать список медианаторов:
        Collections.sort(listOfMedianators);

        //создать пустой новый массив для возвращения:
        Integer[] newArray = array;
        for(int i = 0; i < listOfMedianators.size(); i++){
            int value = listOfMedianators.get(i).getValue();
            newArray[i] = value;
        }
        return newArray;
    }
}
