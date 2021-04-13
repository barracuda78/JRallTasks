package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static void main(String[] args) {
        try {
            //прочитаем имя тега, который нас интересует
            String tag = args[0];

            //прочитаем с консоли имя файла.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = bufferedReader.readLine();
            bufferedReader.close();


            //прочитаем файл и запишем все строки в одну строку StringBuilder
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(fileName));
            while(bufferedReader1.ready()) {
                String s = bufferedReader1.readLine();
                sb.append(s);
            }
            bufferedReader1.close();

            //теперь весь файл у нас в одной строке, которую нужно распарсить:

//Вариант 1 = с помощью jsoup
            Document dc = Jsoup.parse(sb.toString()," ", Parser.xmlParser());
            Elements elem = dc.select(args[0]);
            for (Element elements: elem) {
                System.out.println(elements);
            }
//Вариант 2 = дедовским методом.
 /*           String begin = "<" + tag;
            String end = "</" + tag + ">";

            //запишем номера индексов begin и end в специальные списки.
            List<Integer> listOfBeginIndex = new ArrayList<>();
            List<Integer> listOfEndIndex = new ArrayList<>();


            for (int i = 0; i < sb.length()-begin.length(); i ++){
                if(sb.substring(i, i+begin.length()).equals(begin)) {
                    listOfBeginIndex.add(i);
                    //System.out.println("добавлено в listOfBeginIndex: " + i);
                }
            }
            for (int i = 0; i < sb.length()-end.length()+1; i ++){//
                if(sb.substring(i, i+end.length()).equals(end)) {
                    listOfEndIndex.add(i);
                    //System.out.println("добавлено в listOfEndIndex: " + i);
                }
            }

            String wholeString = sb.toString();

            //если listOfEndIndex.get(i) > listOfBeginIndex.get(i+1), --> вложенный тэг и нужно менять местами listOfEndIndex.get(i) и listOfEndIndex.get(i+1)
            for(int i = 0; i < listOfEndIndex.size()-1; i++){
                if(listOfEndIndex.get(i) > listOfBeginIndex.get(i+1)) {
                    int temp = listOfEndIndex.get(i);
                    listOfEndIndex.set(i, listOfEndIndex.get(i + 1));
                    listOfEndIndex.set(i + 1, temp);
                }

            }

            // В цикле выводим на экран wholeString.substring(listOfBeginIndex.get(i), listOfEndIndex.get(i) + длина закрывающего тэга)
            for(int i = 0; i < listOfEndIndex.size(); i++) {
                if(i==listOfBeginIndex.size()-1){
                    System.out.println(wholeString.substring(listOfBeginIndex.get(i), listOfEndIndex.get(i) + end.length()));
                    break;
                }
                System.out.println(wholeString.substring(listOfBeginIndex.get(i), listOfEndIndex.get(i) + end.length()));
            }
*/
        }
        catch(IOException io){
            io.printStackTrace();
        }
    }


}
