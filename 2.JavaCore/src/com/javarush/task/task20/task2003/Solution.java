package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* Знакомство с properties
Метод main() считывает имя файла с консоли и заполняет runtimeStorage данными из файла.
В методах save() и load() реализуй логику записи в файл и чтения из файла для карты runtimeStorage.
Файл должен быть в формате .properties. Комментарии в файле можно игнорировать.
Про .properties почитать тут - http://ru.wikipedia.org/wiki/.properties

Требования:
1. Метод save() должен сохранять карту runtimeStorage в параметр outputStream.
2. Метод load() должен восстанавливать состояние карты runtimeStorage из параметра inputStream.*/

public class Solution {

    public static Map<String, String> runtimeStorage = new HashMap<>();

    public static void save(OutputStream outputStream) throws Exception {
        //напишите тут ваш код
        Properties p2 = new Properties();
        for(Map.Entry<String, String> pair : runtimeStorage.entrySet()){
            p2.setProperty(pair.getKey(), pair.getValue());
        }
        p2.store(outputStream, "andrey ruzaev");

    }

    public static void load(InputStream inputStream) throws IOException {
        //напишите тут ваш код
        Properties p = new Properties();
        p.load(inputStream);

        for(Map.Entry<Object, Object> pair : p.entrySet()){
            runtimeStorage.put((String)pair.getKey(), (String)pair.getValue());
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileInputStream fos = new FileInputStream(reader.readLine()))
              {
             load(fos);

        } catch (IOException e) {
            e.printStackTrace();
        } catch(Exception ee){
            ee.printStackTrace();
        }

        System.out.println(runtimeStorage);
    }

//    public static void main(String[] args) {
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//             FileInputStream fos = new FileInputStream(reader.readLine());
//             OutputStream outputStream = new FileOutputStream("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2003\\2.properties")) {
//            load(fos);
//            save(outputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch(Exception ee){
//            ee.printStackTrace();
//        }
//
//        System.out.println(runtimeStorage);
//    }
}
