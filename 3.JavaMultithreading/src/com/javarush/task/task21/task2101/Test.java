package com.javarush.task.task21.task2101;

public class Test {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        byte[] result = new byte[ip.length];

        for (int i = 0; i < ip.length; i++) {
            byte b = (byte)(ip[i] & mask[i]);
            result[i] = b;
        }
        return result;
    }

    public static void print(byte[] bytes) {
        for(byte b: bytes){
            String s = String.format("%8s" ,Integer.toBinaryString(b & 0b11111111)).replace(" ", "0");
            System.out.print(s + " ");

        }
        System.out.println();
    }
}
