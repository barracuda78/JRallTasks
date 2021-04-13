package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/*
Что внутри папки?
*/

public class Solution extends SimpleFileVisitor<Path>{
    //private static final List<Path> list = new ArrayList<>();
    private static int foldersCount;
    private static int filesCount;
    private static int bytesCount;

    private static AtomicInteger foldersC = new AtomicInteger(0);
    private static AtomicInteger filesC = new AtomicInteger(0);
    private static AtomicLong bytesC = new AtomicLong(0L);


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        FileVisitResult f = super.visitFile(file, attrs);

        if(Files.isDirectory(file)){
            //foldersCount++;
        }else if(Files.isRegularFile(file)){
            filesC.incrementAndGet();
            bytesC.addAndGet(Files.size(file));
//            filesCount++;
//            bytesCount += Files.size(file);
        }
        return f;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        foldersC.incrementAndGet();
        //foldersCount++;
        return super.preVisitDirectory(dir, attrs);
    }

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();

        String folder = null;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
            folder = bufferedReader.readLine();
        }catch(IOException ex){
            ex.printStackTrace();
        }

        if(folder != null ){
            Path folderPath = Paths.get(folder);
            if(!Files.isDirectory(folderPath) || "".equals(folder)){
                System.out.println(folder + " - не папка");
            }else {

                //Затем посчитай и выведи следующую информацию:
                //
                //Всего папок - [количество папок в директории и поддиректориях]
                //Всего файлов - [количество файлов в директории и поддиректориях]
                //Общий размер - [общее количество байт, которое хранится в директории]
                //
                //Используй только классы и методы из пакета java.nio.

                Files.walkFileTree(folderPath, solution);

                foldersC.decrementAndGet();

                System.out.println("Всего папок - " + (foldersC));
                System.out.println("Всего файлов - " + filesC);
                System.out.println("Общий размер - " + bytesC);
//                System.out.println("Всего папок - " + (foldersCount-1));
//                System.out.println("Всего файлов - " + filesCount);
//                System.out.println("Общий размер - " + bytesCount);
            }

        }else{
            System.out.println(folder + " - не папка");
        }

    }


}
