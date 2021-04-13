package com.javarush.task.task32.task3213;

//import com.sun.org.apache.xpath.internal.operations.String;

import java.io.*;

/* 
Шифр Цезаря
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        if(reader == null)
            return "";
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(reader);

            String s = null;
            while((s = bufferedReader.readLine()) != null){
                sb.append(s);
            }


//        while(bufferedReader.ready()){
//            String s = bufferedReader.readLine();
//            sb.append(s);
//        }

        char[] chars = sb.toString().toCharArray();

        StringBuilder stringToReturn = new StringBuilder("");

        for(int i = 0; i < chars.length; i++){
            chars[i] = (char)((int)chars[i] + key);
            stringToReturn.append(chars[i]);
        }

        return stringToReturn.toString();
    }
}
