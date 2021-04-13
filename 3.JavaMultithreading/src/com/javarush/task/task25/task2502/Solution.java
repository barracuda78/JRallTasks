package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Машину на СТО не повезем!
Инициализируй поле wheels используя данные из loadWheelNamesFromDB.
Выкинь исключение в случае некорректных данных.

Подсказка: если что-то не то с колесами, то это не машина!
Сигнатуры не менять.

Требования:
1. Enum Wheel в классе Solution менять нельзя.
2. Сигнатуры в классе Car менять нельзя.
3. Во время создания машины нужно вызвать метод loadWheelNamesFromDB.
4. В случае возврата неправильных данных о колесах, нужно кинуть исключение.
5. Инициализируй поле wheels полученными данными.
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() {
            //init wheels here
            Wheel[] wArray = Wheel.values();
            List<String> realList = new ArrayList<>();
            for(Wheel wheel : wArray){
                realList.add(wheel.toString());
            }

            wheels = new ArrayList<>();

            String[] sArray = loadWheelNamesFromDB();
            if(sArray.length == 0 || sArray == null) throw new IllegalArgumentException();
            if(sArray.length != 4) throw new IllegalArgumentException();

            for(int i = 0; i < sArray.length; i++){
                if(realList.contains(sArray[i])){
                    wheels.add(Wheel.valueOf(sArray[i]));
                }
                else{
                    throw new IllegalArgumentException();
                }
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
