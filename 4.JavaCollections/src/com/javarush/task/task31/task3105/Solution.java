package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        ///////////////////////////////////////////////1. ВЫЧИТЫВАЮ АРХИВ В МАПУ:///////////////////////////////////////////////////////////////
        //мапа для хранения ZipEntries:
        Map<String, byte[]> map = new HashMap<>();
        ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]));
        ZipEntry ze = null;
        while((ze = zis.getNextEntry()) != null){
            //ключ в мапе - имя файла:
            String key = ze.getName();
            byte[] buffer = new byte[1024];
            int len;
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
            while((len = zis.read(buffer)) > 0){
                baos.write(buffer, 0, len);
            }
            //значение в мапе - массив байтов этого файла в архиве:
            byte[] value = baos.toByteArray();
            //Заполняю мапу в цикле по ZipEntry:
            map.put(key, value);
            zis.closeEntry();
        }
        zis.close();
        ///////////////////////////////////////////////2. ЗАПИСЫВАЮ АРХИВ ЗАНОВО ИЗ МАПЫ + ИЗ ФАЙЛА:///////////////////////////////////////////////////////////////
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]));
        File file = new File(args[0]);
        //Если такого файла еще нет в архиве:
        System.out.println("file = " + file.getName());
        if(!map.containsKey("new/" + file.getName())){
            //сначала записываю заново все ZipEntry из мапы:
            for(Map.Entry<String, byte[]> pair : map.entrySet()){
                ZipEntry zipEntry = new ZipEntry(pair.getKey());
                zos.putNextEntry(zipEntry);
                zos.write(pair.getValue());
                zos.flush();
                zos.closeEntry();
            }

            //потом добавляю новый файлик в новую ZipEntry:
            File newAddedFile = new File("new/" + file.getName());
            ZipEntry zipEntry = new ZipEntry(newAddedFile.toString());
            zos.putNextEntry(zipEntry);
            Files.copy(file.toPath(), zos);
            zos.flush();
            zos.closeEntry();
            zos.close();

            //Если такой файлик уже есть в архиве:
        }else{
            System.out.println("такой файл есть!");
            //итерируюсь по мапе:
            for(Map.Entry<String, byte[]> pair : map.entrySet()){
                System.out.println(pair.getKey());
                ZipEntry zipEntry = new ZipEntry(pair.getKey());
                zos.putNextEntry(zipEntry);
                //если ключ мапы - имя того самого файлика:
                if(pair.getKey().equals("new/" + file.getName())){
                    System.out.println("есть такой...");
                    //обновляю его через Files.copy
                    //File newAddedFile = new File("new/" + file.getName());
                    //File newAddedFile = new File(file.getName());
                    Files.copy(Paths.get(args[0]), zos);
                    zos.flush();
                    zos.closeEntry();
                    //если это другие файлы архива:
                }else{
                    //пишу их из байт-массива из мапы:
                    zos.write(pair.getValue());
                    zos.flush();
                    zos.closeEntry();
                }
            }
            zos.close();

        }
    }
}
