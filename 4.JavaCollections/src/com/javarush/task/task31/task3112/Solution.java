package com.javarush.task.task31.task3112;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {

        if (Files.notExists(downloadDirectory))
            Files.createDirectories(downloadDirectory);

        URL url = new URL(urlString);

//        URLConnection conn = url.openConnection();
//        InputStream input = conn.getInputStream();

        Path tempPath = Files.createTempFile("temp", ".tmp");

        Files.copy(url.openStream(), tempPath, StandardCopyOption.REPLACE_EXISTING);

        //приклеить к downloadDirectory имя скачанного файла из urlString:
        String downloadedFileName = urlString.substring(urlString.lastIndexOf("/") + 1);  //это учитывая начальный слэш
        String newFileName = downloadDirectory.toString() + "/" +  downloadedFileName;

        return Files.move(tempPath,  Paths.get(newFileName), StandardCopyOption.REPLACE_EXISTING);

    }
}
