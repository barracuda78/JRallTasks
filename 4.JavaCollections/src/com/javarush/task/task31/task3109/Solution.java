package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/* 
Читаем конфиги
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {

        Path p = Paths.get(fileName);
        Properties properties = new Properties();
        //если не существует:
        if(!Files.exists(p)){
            return properties ;
        }



        //если .xml:
        if(fileName.endsWith(".xml")){
            try {
                properties.loadFromXML(new FileInputStream(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties;
        }

        //если любой другой файл:

        else{
            try {
                properties.load(new FileInputStream(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return properties;
        }
    }
}
