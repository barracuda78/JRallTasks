package com.javarush.task.task22.task2207;

import java.io.*;
import java.util.*;

/*
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        try{
            //считал с консоли имя файла.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = bufferedReader.readLine();
            bufferedReader.close();

            //прочитал файл в массив строк, сконкатенировал в стрингбилдер sb.
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(new File(fileName)));
            while(bufferedReader1.ready()) {
                sb = sb.append(bufferedReader1.readLine());
                sb = sb.append(" ");//добавляю пробел в конце каждой строки файла.
            }
            sb = sb.deleteCharAt(sb.length()-1); //удаляю пробел в конце последней строки, который добавлен в цикле while выше.
            bufferedReader1.close();


            //из этой строки StringBuilder sb создаю массив строк, разделенных пробелами.
            String[] sArray = sb.toString().split(" ");

            //в цикле перебираю все элементы массива строк sArray.
            //Если встречается строка, для которой есть реверс,
            //добавляю строку в Set<Pair> set.
            Set<Pair> set = new LinkedHashSet<>();
            for(int i = 0; i <sArray.length-2; i++) {
                for (int j = i+1; j < sArray.length; j++) {
                    if (sArray[i].equals(((new StringBuilder(sArray[j])).reverse()).toString())) {
                        Pair p = new Pair();
                        p.first = sArray[i];
                        p.second = sArray[j];

                        //В качестве проверки уникальности пар добавляю все пары в SET.
                        set.add(p);
                    }
                }
            }
            //добавляю все элементы из SET в существующий LinkedList reverse.
            result.addAll(set);

//-----------------------test----------------------
            for(Pair p : result){
                System.out.println(p.toString());
            }
            System.out.println("--------------");
            System.out.println(result.get(0));
            System.out.println(result.get(1));
//--------------------end of test----------------------
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }

    public static class Pair {
        String first;
        String second;

        public Pair(){

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
//            return first == null && second == null ? "" :
//                    first == null ? second :
//                            second == null ? first :
//                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;
            return first + " " + second;

        }
    }

}

