package com.javarush.task.task04.task0413;

/* 
День недели
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String sAge = bufferedReader.readLine(); //читаем строку с клавиатуры
        int day = Integer.parseInt(sAge); //преобразовываем строку в число.


        String[] seasons  = new String[4]; /* объявили и создали массив. Java выделила память под массив из 4 строк, и сейчас в каждой ячейке записано значение null (поскольку строка — ссылочный тип)*/

        seasons[0] = "Winter"; /* в первую ячейку, то есть, в ячейку с нулевым номером мы записали строку Winter. Тут мы получаем доступ к нулевому элементу массива и записываем туда конкретное значение  */
        seasons[1] = "Spring"; // проделываем ту же процедуру с ячейкой номер 1 (второй)
        seasons[2] = "Summer"; // ...номер 2
        seasons[3] = "Autumn"; // и с последней, номер 3
    }
}