package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.*;

/*
Составить цепочку слов
*/
public class Solution {
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
            //String[] sArray = sb.toString().toLowerCase().split(" ");
            String[] sArray = sb.toString().split(" ");

        //передаю этот массив строк в метод getLine().
        StringBuilder result = getLine(sArray);
        System.out.println(result.toString());


        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public static StringBuilder getLine(String... words) {

        StringBuilder sb = new StringBuilder();
        List<String> strings = new LinkedList<>();

        for (int i = 0; i < words.length; i++) {
            strings.add(words[i].toLowerCase().trim()); }

        boolean dontSuits = true;

        while (dontSuits){
            Collections.shuffle(strings);
            int wordGood = 0;
            for (int i = 0; i < strings.size() - 1; i++){
                if(strings.get(i).charAt(strings.get(i).length()-1) ==
                        strings.get(i+1).charAt(0)){
                    wordGood++; } }
            if(wordGood == words.length-1){ dontSuits = false; } }

        for(String s : strings){
            for(int i = 0; i < words.length; i++){
                if (s.equalsIgnoreCase(words[i])){ sb.append(words[i]);
                    sb.append(" "); } } }

        return sb.deleteCharAt(sb.length() - 1);

    }
    //это мой правильный метод:
/*
    public static StringBuilder getLine(String... words) {

        //создать массив строк w слов, аналогичный массиву words, только со строчными буквами.
        String[] w = new String[words.length];
        for(int i = 0; i < words.length; i++){
            w[i] = words[i].toLowerCase();
        }

        StringBuilder sb = new StringBuilder(); //стрингбилдер для возвращенного значения
        if(w.length == 0) return new StringBuilder();// Метод getLine должен возвращать пустую строку (пустой StringBuilder) в случае если ему не были переданы параметры (слова).

        List<String> list = new ArrayList<>();
        for(String s : w){
            list.add(s);
        }

        boolean dontSuits = true;

        while(dontSuits) {
            Collections.shuffle(list);
            //проверка
            int goodWordsCounter = 0;
            for(int i = 0; i < list.size()-1; i++){
                if(list.get(i).charAt(list.get(i).length()-1) == list.get(i+1).charAt(0)){
                    goodWordsCounter++;
                }
            }

            if(goodWordsCounter == words.length-1){
                dontSuits = false;
            }
        }


        for(String s : list){
            for(int i = 0; i < words.length; i++){
                if (s.equalsIgnoreCase(words[i])){
                    sb.append(words[i]);
                    sb.append(" ");
                }
            }

        }
        //удаляем пробел в конце sb:
        sb.deleteCharAt(sb.length()-1);

        return sb;
    }
    */

//это чужой неправильный метод - первый вариант:
/*public static StringBuilder getLine(String... words) {
    if (words == null || words.length == 0) return new StringBuilder();

    List<StringBuilder> strings = new LinkedList<>();

    for (int i = 0; i < words.length; i++) {
        strings.add(new StringBuilder(words[i].toLowerCase().trim()));
    }
    final Random RANDOM = new Random();
    int a = RANDOM.nextInt(5);

    StringBuilder sb = new StringBuilder(strings.get(a));
    // start - первая буква
    char start = sb.charAt(0);
    char end = sb.charAt(sb.length() - 1);

    for (StringBuilder str: strings){

        char strStart = str.charAt(0);
        char strEnd = str.charAt(str.length() - 1);

        if (start == strEnd) {
            sb.insert(0, str + " ");
            start = strStart;
        }
        if (end == strStart){
            sb.append(" " + str);
            end = strEnd;
        }
    }
    return sb;
}*/

//это чужой неправильный метод - второй вариант:

}
