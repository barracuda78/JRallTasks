package com.javarush.task.task40.task4006;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;

/* 
Отправка GET-запроса через сокет
*/
//Отправка GET-запроса через сокет
//Перепиши реализацию метода getSite, он должен явно создавать и использовать сокетное соединение Socket с сервером.
//Адрес сервера и параметры для GET-запроса получи из параметра url.
//Порт используй дефолтный для http (80).
//Классы HttpURLConnection, HttpClient и т.д. не использовать.
//Не оставляй закомементированный код.
//
//
//Требования:
//1. Метод getSite должен создавать объект класса Socket с правильными параметрами (String host, int port).
//2. Метод getSite должен записать в OutputStream правильный запрос.
//3. Метод getSite должен выводить на экран InputStream сокета.
//4. Метод getSite не должен использовать HttpURLConnection или HttpClient.
public class Solution {
    public static void main(String[] args) throws Exception {
        getSite(new URL("http://javarush.ru/social.html"));
    }

    public static void getSite(URL url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseLine;

            while ((responseLine = bufferedReader.readLine()) != null) {
                System.out.println(responseLine);
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}