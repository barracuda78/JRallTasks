package com.javarush.task.task31.task3102;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

/* 
Находим все файлы
*/

public class Solution {

    static List<String> globalList = new LinkedList<>();

    public static List<String> getFileTree(String root) throws IOException {


        Files.walkFileTree(Paths.get(root), new MyFileVisitor());

        List<String> list = new LinkedList<>(globalList);

        //list.stream().forEach(x -> System.out.println("файлик: " + x));

        return list;

    }

    public static void main(String[] args) {
        try {
            getFileTree("C:\\coding\\javarushtasks\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task31\\task3101\\bin");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyFileVisitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException{
            if(!Files.isDirectory(filePath)){
                globalList.add(filePath.toString());
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
