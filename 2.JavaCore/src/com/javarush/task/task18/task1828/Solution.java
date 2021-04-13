package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Solution {
    public static void main(String[] args) throws IOException {
        final String FORMAT = "%-8s%-30s%-8s%-4s";

        if(args.length > 0) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            reader.close();

            ArrayList<String> list = new ArrayList<>();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            while (fileReader.ready())
                list.add(fileReader.readLine());

            fileReader.close();


            switch (args[0]) {
                case "-u": {

                    String idS = args[1];
                    String productNameS = args[2];
                    String priceS = args[3];
                    String quantityS = args[4];

                    //обрежем строковые значения входных аргуметов до необходимой длины.
                    if (idS.length() > 8) idS = idS.substring(0, 8).trim();
                    if (productNameS.length() > 30) productNameS = productNameS.substring(0, 30);
                    if (priceS.length() > 8) priceS = priceS.substring(0, 8);
                    if (quantityS.length() > 4) quantityS = quantityS.substring(0, 4);

                    //парсим строковые значения входных аргуметов в соответсвующие типы. Хотя можно и без этого.
                    //Integer id = Integer.parseInt(idS.trim());
                    //String productName = productNameS;
                    //Double price = Double.parseDouble(priceS.trim());
                    //Integer quantity = Integer.parseInt(quantityS.trim());

                    //итерируемся по списку строк из файла. Встречаем нужный id. Подменяем строку на строку из параметров.
                    for (int i = 0; i < list.size(); i++) {
                        String line = list.get(i);
                        String curId = line.substring(0, 8).trim();


                        if (idS.equals(curId)) list.set(i, String.format(FORMAT, idS, productNameS, priceS, quantityS));
                    }

                    //записываем все новые строки в файл поверх старых строк.
                    PrintWriter printWriter = new PrintWriter(fileName);
                    for (String s : list)
                        printWriter.println(s);
                    printWriter.close();

                    break;
                }

                case "-d": {
                    String idS = args[1];             //----------------->раскомментить после теста
                    //String idS = "198478  ";          //---------------> закоммментить после теста

                    //подрезаем параметр до требуемой длины, если нужно:
                    if (idS.length() > 8) idS = idS.substring(0, 8);

                    //итерируемся по списку строк из файла. Если находим строку с нужным id, удаляем ее из списка.
                    Iterator<String> iter = list.iterator();
                    while (iter.hasNext()) {
                        String product = iter.next();
                        if (product.startsWith(idS))
                            iter.remove();
                    }

                    //записываем новый список строк в файл поверх старых строк.
                    PrintWriter fileWriter = new PrintWriter(fileName);
                    for (String s : list)
                        fileWriter.println(s);
                    fileWriter.close();


                    break;
                }
            }
        }
        else{
            //ничего не делаем, если нет параметров.
        }

    }
}