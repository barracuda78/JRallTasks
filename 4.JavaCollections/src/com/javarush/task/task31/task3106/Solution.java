package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
    public static void main(String[] args) {

        //1) Делаем список из имен кусков архива и сортируем его
        //2) Делаем лист InputStream'ов для каждого из кусков архива из пункта 1
        //3) Создаем поток записи в файл который у нас args[0]
        // и поток ZipInputStream в конструкторе которому передаем SequenceInputStream который создаем,
        // передавая в конструктор Collections.enumeration(inputStreams),
        // где zipInputStreams это как раз лист inputStream'ов из пункта 2
        //4) Копируем данные и закрываем потоки

        //public class Solution {
        //    public static void main(String[] args) throws IOException {
        //        Arrays.sort(args, 1, args.length); //сортируем
        //        ZipInputStream zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(
        //                Arrays.asList(new FileInputStream(args[1]), new FileInputStream(args[2]), new FileInputStream(args[3])))));
        //        FileOutputStream fos = new FileOutputStream(args[0]);
        //        ZipEntry ze;
        //        byte[] buffer = new byte[1024 * 1024];//1MB buffer
        //        while ((ze = zis.getNextEntry()) != null) {
        //            System.out.println("Unzipping " + ze.getName());
        //            int byteBuffer;
        //            while ((byteBuffer = zis.read(buffer)) != -1) {
        //                fos.write(buffer, 0, byteBuffer);
        //            }
        //            zis.closeEntry();
        //        }
        //        fos.close();
        //        zis.close();
        //    }
        //}


        try {
            List<String> list = new ArrayList<>(Arrays.asList(args));
            list.remove(0);
            Collections.sort(list);

            List<InputStream> iList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                iList.add(new FileInputStream(list.get(i)));
            }

            FileOutputStream fos = new FileOutputStream(args[0]);

            ZipInputStream zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(iList)));

            ZipEntry ze;
            byte[] buffer = new byte[1024 * 1024];
            while((ze = zis.getNextEntry()) != null){
                int n;
                while((n = zis.read(buffer, 0, buffer.length)) != -1){
                    fos.write(buffer, 0, n);
                }
                zis.closeEntry();
            }
            fos.flush();
            zis.close();
            fos.close();

        }catch(FileNotFoundException e){
            e.getStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
