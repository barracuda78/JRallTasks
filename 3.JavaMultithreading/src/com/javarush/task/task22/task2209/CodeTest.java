package com.javarush.task.task22.task2209;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeTest {



    public static void main(String[] args) {
//        Pattern p = Pattern.compile("Set(Value)");
//        Matcher m = p.matcher("Set or SetValue");
//        while(m.find()){
//            System.out.println(m.start() + " : " + m.group());
//        }

        Pattern p = Pattern.compile("<([A-Z][A-Z0-9]*)[^>]*>.*?</\\1>");
        Matcher m = p.matcher("This is a <EM>first</EM> test Thi s is a <H1>second</H1> test");
        while(m.find()){
            System.out.println(m.start() + " : " + m.group());
        }

        //System.out.println("EditPad Lite".replaceAll("EditPad (Lite|Pro)", "EditPad $1 version"));


    }
}
