package com.javarush.task.task18.task1822;


import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        int idOfProduct = Integer.parseInt((args[0]));  //---------------------> РАЗКОММЕНТИТЬ
        //int idOfProduct = 03;
        ArrayList<String> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = bufferedReader.readLine();

            FileReader fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                list.add(line);
            }
            bufferedReader.close();
            fileReader.close();

            ArrayList<Product> listOfProducts = new ArrayList<>();
            for(String string : list){
                Product product = new Product();
                //product.id = Integer.parseInt(string.substring(0, string.indexOf(" ")));

                String[] stringArray = string.split(" ");
                product.id = Integer.parseInt(stringArray[0]);
                if(stringArray.length == 4){
                    product.productName = stringArray[1];
                }
                else{
                    String s = "";
                    for(int i = 0; i < stringArray.length-3; i++){
                    s += stringArray[i+1] + " ";
                    }
                    product.productName = s.substring(0, s.length()-1);
                }
                product.price = Double.parseDouble(stringArray[stringArray.length-2]);
                product.quantity = Integer.parseInt(stringArray[stringArray.length-1]);

                listOfProducts.add(product);
            }

            for(Product product : listOfProducts){
                if(idOfProduct == product.id){
                    System.out.println(product.toString());
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    static class Product{
        int id;
        String productName;
        double price;
        int quantity;

        @Override
        public String toString(){
            return id + " " + productName + " " + price + " " + quantity;
        }

    }
}
