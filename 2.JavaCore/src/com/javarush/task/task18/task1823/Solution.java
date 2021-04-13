package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static final Object lock = new Object();
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s = null;
            while (true) {
                s = bufferedReader.readLine();
                if(s.equals("exit")) break;
                ReadThread rt = new ReadThread(s);
                rt.start();
                rt.join();

            }
            bufferedReader.close();
//-------------------------------------
//            for(Map.Entry<String, Integer> pair : resultMap.entrySet()){
//                String key = pair.getKey();
//                Integer value = pair.getValue();
//
//                System.out.println(key + " : " + value);
//            }
//---------------------------------------
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public static class ReadThread extends Thread {
        String fileName = null;
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;

        }
        @Override
        public void run(){
            try {
                //Записываем все байты из файла в LIST
                FileInputStream fileInputStream = new FileInputStream(fileName);
                List<Integer> list = new ArrayList<>();
                while(fileInputStream.available() > 0){
                    list.add(fileInputStream.read());
                }
                fileInputStream.close();

                //помещаем все байты из листа в мапу.
                Map<Integer, Integer> map = new HashMap<>();
                for(int i = 0; i < list.size(); i++){
                    if(map.containsKey(list.get(i))){
                        map.put(list.get(i), map.get(list.get(i))+1);
                    }
                    else{
                        map.put(list.get(i), 1);
                    }

                }
                int max = 0;
                for(Map.Entry<Integer, Integer> pair : map.entrySet()){
                    Integer key = pair.getKey();
                    Integer value = pair.getValue();

                    if( value >= max) max = value;
                }
                for(Map.Entry<Integer, Integer> pair : map.entrySet()){
                    Integer key = pair.getKey();
                    Integer value = pair.getValue();

                    if(value == max) writeToMap(fileName, key);
                }

            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        // implement file reading here - реализуйте чтение из файла тут
    }

    private static void writeToMap(String fileName, Integer key){
        synchronized (lock){
            resultMap.put(fileName, key);
        }
    }
}
