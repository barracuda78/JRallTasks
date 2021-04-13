package com.javarush.task.task32.task3211;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

/* 
Целостность информации
*/

public class Solution {
    public static void main(String... args) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(new String("test string"));
        oos.flush();
        System.out.println(compareMD5(bos, "5a47d12a2e3f9fecf2d9ba1fd98152eb")); //true
    }

    public static boolean compareMD5(ByteArrayOutputStream byteArrayOutputStream, String md5) throws Exception {
        //1. получить алгоритм хеширования из строки md5:
//        String method = null;
//        MessageDigest ethalonMessageDigest = MessageDigest. ...

//        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//        byte[] data1 = byteArrayOutputStream.toByteArray();
//
//        byte[] result = messageDigest.digest(data1);
//
//        BigInteger bigInteger = new BigInteger(1, result);
//
//        return bigInteger.toString(16).equals(md5);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BigInteger i = new BigInteger(1, messageDigest.digest(byteArrayOutputStream.toByteArray()));
        return String.format("%032x", i).equals(md5);
    }
}
