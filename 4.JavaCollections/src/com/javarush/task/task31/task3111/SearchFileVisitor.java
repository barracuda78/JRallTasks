package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName = "";
    private String partOfContent = "";
    private int minSize = 0;
    private int maxSize = Integer.MAX_VALUE;
    private List<Path> foundFiles = new LinkedList<>();


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length

        //3. Если в SearchFileVisitor задан критерий поиска partOfName,
        // метод visitFile должен добавить файл в foundFiles,
        // если в названии содержится строка partOfName.
        String s = file.getFileName().toString();
        String str = new String(content);

        boolean b1 =  s.contains(partOfName);
        boolean b2 = str.contains(partOfContent);
        boolean b3 = Files.size(file) < maxSize;
        boolean b4 = Files.size(file) > minSize;


        if(b1 && b2 && b3 && b4){
            foundFiles.add(file);
        }

//        if(partOfName != null && s.contains(partOfName)){
//            foundFiles.add(file);
//        }

        //4. Если в SearchFileVisitor задан критерий поиска partOfContent,
        // метод visitFile должен добавить файл в foundFiles,
        // если в содержимом встречается строка partOfContent.
//        if(partOfContent != null && str.contains(partOfContent)){
//            //String str = new String(content);
//
//                foundFiles.add(file);
//
//        }

        //5. Если в SearchFileVisitor задан критерий поиска maxSize,
        // метод visitFile должен добавить файл в foundFiles,
        // если размер файла меньше maxSize.
//        if(maxSize > 0 && Files.size(file) < maxSize){
//            foundFiles.add(file);
//        }

        //6. Если в SearchFileVisitor задан критерий поиска minSize,
        // метод visitFile должен добавить файл в foundFiles,
        // если размер файла больше minSize.
//        if(minSize > 0 && Files.size(file) > minSize){
//            foundFiles.add(file);
//        }


        return super.visitFile(file, attrs);
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }
}
