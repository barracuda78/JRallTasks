package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Проход по дереву файлов
*/

//C:\coding\javarushtasks\JavaRushTasks\4.JavaCollections\src\com\javarush\task\task31\task3101\bin
//C:\coding\javarushtasks\JavaRushTasks\4.JavaCollections\src\com\javarush\task\task31\task3101\resultFileAbsolutePath.txt
public class Solution {

    private ArrayList<File> list = new ArrayList<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        //1.
        //получаем файл resultFileAbsolutePath.txt:
        File source = new File(args[1]);
        //получаем его папку: source.getParent() и
        //создаем строку с полным путём к переименовываемому файлу resultFileAbsolutePath.txt
        String s = source.getParent() + "\\allFilesContent.txt";
        File renamedSource = new File(s);
        //проверяем если в этой папке нет файла с именем resultFileAbsolutePath.txt (renamedSource),
        // то переименовываем resultFileAbsolutePath.txt в resultFileAbsolutePath.txt.

        //if(!FileUtils.isExist(renamedSource)){
            //2. Переименовать resultFileAbsolutePath в resultFileAbsolutePath.txt
            // (используй метод FileUtils.renameFile(),
            // и, если понадобится, FileUtils.isExist()).
            FileUtils.renameFile(source, renamedSource);
        //}

        //3: Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
        File sourceFolder = new File(args[0]);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(renamedSource, true))){

            Files.walkFileTree(sourceFolder.toPath(), solution.new MyFileVisitor());

            //3.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
            Collections.sort(solution.list, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            //3.2. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 3.1.
            // После каждого тела файла записать "\n".
            //Все файлы имеют расширение txt.
            //В качестве разделителя пути используй "/".
            for(File f : solution.list){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
                //FileInputStream fis = new FileInputStream(f);
                while(bufferedReader.ready()){
                    bufferedWriter.write(bufferedReader.readLine());
                }
                //bufferedWriter.write(System.lineSeparator());
                bufferedWriter.write("\n");
                bufferedWriter.flush();
                bufferedReader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    class MyFileVisitor extends SimpleFileVisitor<Path>{
        @Override
        public FileVisitResult visitFile(Path filePath, BasicFileAttributes attrs) throws IOException{

            //System.out.println("файлик: " + filePath.getFileName());
            //Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
            if(Files.size(filePath) <= 50 && !Files.isDirectory(filePath)){
                list.add(filePath.toFile());
            }
            return FileVisitResult.CONTINUE;
        }
    }

}


