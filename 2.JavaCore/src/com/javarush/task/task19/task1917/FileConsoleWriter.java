package com.javarush.task.task19.task1917;

/* 
Свой FileWriter
*/

import java.io.*;


public class FileConsoleWriter {
    //приватная переменная типа FileWriter
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
//пять конструкторов
    //1-й констркутор
    public FileConsoleWriter(String fileName) throws IOException{
        fileWriter = new FileWriter(fileName);
    }
    //2-й констркутор
    public FileConsoleWriter(String fileName, boolean append) throws IOException{
        fileWriter = new FileWriter(fileName, append);
    }
    //3-й констркутор
    public FileConsoleWriter(File file) throws IOException{
        fileWriter = new FileWriter(file);
    }
    //4-й констркутор
    public FileConsoleWriter(File file, boolean append) throws IOException{
        fileWriter = new FileWriter(file, append);
    }
    //5-й констркутор
    public FileConsoleWriter(FileDescriptor fd){
        fileWriter = new FileWriter(fd);
    }

    //пять методов.
    //1
    public void close() throws IOException{
        fileWriter.close();
    }
    //2
    public void write(char[] cbuf, int off, int len) throws IOException{

        fileWriter.write(cbuf, off, len);
        for(int i = off; i < cbuf.length; i++){
            if (i == off + len) break;
            System.out.print(cbuf[i]);
        }
    }
    //3

    public void write(int c) throws IOException{
        fileWriter.write(c);
        System.out.println(c);
    }
    //4

    public void write(String str) throws IOException{
        fileWriter.write(str);
        bufferedWriter.write(str);
        bufferedWriter.flush();
        bufferedWriter.close();
        //System.out.println(str);
    }
    //5

    public void write(String str, int off, int len) throws IOException{
        fileWriter.write(str, off, len);
        char[] cbuf = str.toCharArray();
        for(int i = off; i < cbuf.length; i++) {
            if (i == off + len) break;
            System.out.print(cbuf[i]);
        }
    }
    //6

    public void write(char[] cbuf) throws IOException{
        fileWriter.write(cbuf);
        System.out.println(cbuf);
    }


    public static void main(String[] args) {
        try {
            String fileName = "C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task19\\task1917\\1";
            FileConsoleWriter fileConsoleWriter = new FileConsoleWriter(fileName);

            fileConsoleWriter.write("Andrey Ruzaev");
            fileConsoleWriter.close();
        }
        catch(IOException io){
            io.printStackTrace();
        }
    }

}
