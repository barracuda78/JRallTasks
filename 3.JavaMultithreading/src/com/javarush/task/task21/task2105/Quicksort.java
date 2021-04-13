package com.javarush.task.task21.task2105;

import java.util.HashMap;
import java.util.Map;

public class Quicksort {
    public static void main(String[] args) {
        int[] array = {0,5,15,50,89,90,91,92,98,99};
        quicksort(array);
    }

    public static int[] quicksort(int[] array){
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(Integer i : array ){
            if (i > max) max = i;
            if (i < min) min = i;
        }

        int mid = (max+min) / 2;
        //System.out.println("это значение mid = " + mid);
        int middle = 0;

            //мапа с ключем - индекс ячейки массива, значением - разница между значением масиива и средним значением.
        Map<Integer, Integer> map = new HashMap<>();

        int minDifference = 0;
        for(int i = 0; i < array.length; i++){
            //мне нужно найти число в массиве, разница которого с mid по модулю будет наименьшей.
            int dif = array[i] - mid;
            if (dif < 0) dif *= -1;
            map.put(i, dif);
        }

        int mapMinValue = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            int key = pair.getKey();
            int value = pair.getValue();
            //System.out.println("Key = " + key + ", value = " + value);
            if (value  <= mapMinValue ) mapMinValue = value;
        }
        //System.out.println("mapMinValue = " + mapMinValue);
        for(Map.Entry<Integer, Integer> pair : map.entrySet()){
            int key = pair.getKey();
            int value = pair.getValue();
            if (value  ==  mapMinValue) middle = array[key];
        }
        //System.out.println("среднее значение массива = " + middle);

        //Ура! Я нашел среднее значение в массиве! это int middle.




        return array;
    }
}


