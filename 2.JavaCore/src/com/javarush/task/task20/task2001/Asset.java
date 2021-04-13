package com.javarush.task.task20.task2001;

import java.io.*;

public class Asset {
    //мой код --- создам пустой конструктор для простоты
    public Asset(){

    }
    //--конец моего кода
    public Asset(String name, double price) {
        this.name = name;
        this.price = price;
    }

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Asset asset = (Asset) o;

        if (Double.compare(asset.price, price) != 0) return false;
        return name != null ? name.equals(asset.name) : asset.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    //дальше - это моё творчество.
    public void save(OutputStream outputStream) throws Exception {
            PrintWriter printWriter = new PrintWriter(outputStream);

            //String isNamePresent = name !=null ? "yes" : "no";    //запишем имя name
            //printWriter.println(isNamePresent);
            //printWriter.flush();
            //if (name !=null)
            printWriter.println(name);
            printWriter.println(price);
            printWriter.flush();


    }

    public void load(InputStream inputStream) throws Exception{
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        name = bufferedReader.readLine();
        price = Double.parseDouble(bufferedReader.readLine());

    }
}
