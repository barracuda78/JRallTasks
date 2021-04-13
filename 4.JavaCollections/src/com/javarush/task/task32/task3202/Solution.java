package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем из потока
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter stringWriter = new StringWriter();

        if(is != null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String s = null;
            StringBuilder sb = new StringBuilder();

            while (bufferedReader.ready()) {
                s = bufferedReader.readLine();
                sb.append(s);
            }

            stringWriter.write(sb.toString());
        }

        return stringWriter;
    }
}
