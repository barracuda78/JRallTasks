package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Десериализация JSON объекта
*/

public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        if(fileName == null)
            return null;
//        Path p = Paths.get(fileName);
//        if(!Files.exists(p))
//            return null;
//        if(Files.isDirectory(p))
//            return null;

//        OutputStream outputStream = new ByteArrayOutputStream();
//        Files.copy(p, outputStream);

        File file = new File(fileName);

        if(file.isDirectory())
            return null;

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(file, clazz);
    }

    public static void main(String[] args) {

    }
}
