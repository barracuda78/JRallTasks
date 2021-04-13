package com.javarush.task.task18.task1820;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> listOfFileNames = readFileNames();
        String string = readFileToString(listOfFileNames.get(0));
//            System.out.println("Это строка: ");
//            System.out.println(string);
        ArrayList<Double> listOfDouble = readString(string);
//            System.out.println("Это лист Double: ");
//            for(Double ddd : listOfDouble) {
//                System.out.print(ddd + ", ");
//            }
        ArrayList<Integer> listOfInteger = doubleToInteger(listOfDouble);
//            System.out.println();
//            System.out.println("Это лист Integer: ");
//            for(Integer iii : listOfInteger) {
//                System.out.print(iii + ", ");
//            }
//        System.out.println();
        writeTofile(listOfFileNames.get(1), listOfInteger);
    }

    //метод для чтения имен файлов в ArrayList.
    public static ArrayList<String> readFileNames(){
        ArrayList<String> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            for (int i = 0; i < 2; i++) {
                list.add(bufferedReader.readLine());
            }
            bufferedReader.close();
        }
        catch(IOException i){
            i.printStackTrace();
        }
        return list;
    }


    //метод для чтения содержимого файла в String.
    public static String readFileToString(String fileName){
        String string = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            //int available = fileInputStream.available();
            //listFirstFile.add(available); //записали в позицию НОЛЬ листа реальное количество байт.
            while (bufferedInputStream.available() > 0){
                char ch = (char)bufferedInputStream.read();
                string = string + ch;
            }

            bufferedInputStream.close();
            fileInputStream.close();
        }
        catch (IOException i){
            i.printStackTrace();
        }
        return string;
    }


    //метод для чтения String и помещения double в ArrayList.
    public static ArrayList<Double> readString(String string){
        ArrayList<Double> listOfDouble = new ArrayList<>();
        String[] stringArray = string.split(" ");
        for (int i = 0; i < stringArray.length; i++) {
            listOfDouble.add(Double.parseDouble(stringArray[i]));
        }
        return listOfDouble;
    }


    //метод для округления double и записи их в ArrayList<Integer>.
    public static ArrayList<Integer> doubleToInteger(ArrayList<Double> listOfDouble){
        ArrayList<Integer> listOfInteger = new ArrayList<>();
        Double newDouble = 0.0;
        for (int i = 0; i < listOfDouble.size() ; i++) {
            if(listOfDouble.get(i) >= 0) {
                newDouble = new BigDecimal(listOfDouble.get(i)).setScale(0, RoundingMode.HALF_UP).doubleValue();
            }
            else{
                newDouble = new BigDecimal(listOfDouble.get(i)).setScale(0, RoundingMode.HALF_DOWN).doubleValue();
            }
            String s = newDouble.toString();
            s = s.substring(0, s.indexOf("."));
            int newInt = Integer.parseInt(s);
            listOfInteger.add(newInt);
        }
        return listOfInteger;
    }

    //метод для записи ArrayList<Integer> в файл 2:
    public static void writeTofile(String fileName, ArrayList<Integer> listOfInteger){
        try {

            File f = new File(fileName);
            PrintWriter out = new PrintWriter(new FileWriter(f));
            for (int element: listOfInteger){
                out.print(element + " ");
            }
            out.close();
        }
        catch (IOException i){
            i.printStackTrace();
        }
    }
}
