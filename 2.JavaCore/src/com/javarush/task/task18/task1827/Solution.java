package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        if(args.length > 0){                                       //после теста заменить == на > в этой строке кода.
            String crud = args[0];                                // раскомментить
            String productName = args[1];                          //-----------разкомментить
            String price = args[2];                               //-----------разкомментить
            String quantity = args[3];                           //-----------разкомментить
//            String crud ="-c";
//            String productName = "Костюм";
//            String price = "16.99";
//            String quantity = "1";
            if(crud.equals("-c")) {
                //обрежем строки под необходимый размер, если они больше этого размера.
                if(productName.length() > 30) productName = productName.substring(0, 30);
                if(price.length() > 8) price = price.substring(0, 8);
                if(quantity.length() > 4) quantity = quantity.substring(0, 4);

                //создадим ArrayList<String> для записи строк из файла.
                List<String> list = new ArrayList<>();
                //запишем в лист все строки из файла.
                FileReader fileReader = new FileReader(fileName);
                BufferedReader bufferedReader1 = new BufferedReader(fileReader);
                while(bufferedReader1.ready()){
                   String s = bufferedReader1.readLine();
                   list.add(s);
                }
                bufferedReader1.close();
          //прочитаем максимальный id из файла. Для этого:

                //создадим список всех id из файла, итерируясь по списку строк из файла и парся все значения, пока не встретится пробел. Или пока не будет 8 значений..
                List<Integer> idList = new ArrayList<>();
                for(String string : list){
                    String stringID = "";
                    //представим строку из файла в виде массива символов. Для того, чтобы итерироваться по массиву символов, обирая строковое значение id из 8-ми символов, или найти первый пробел и перестать.
                    char[] charArray = string.toCharArray();
                    //итерируемся по массиву символов.
                    for(int i = 0; i < 8; i++){
                        //пока не встречаем символ пробела, собираем из символов строку.
                        if(charArray[i] != ' '){

                            stringID = stringID + charArray[i];
                        }
                        else{
                            //если встретили пробел - выходим из цикла.
                            break;
                        }
                    }
                    idList.add(Integer.parseInt(stringID));
                }

                //найдем максимальный id  в списке всех id  из файла:
                int id = Integer.MIN_VALUE;   // - это наш ID
                for(Integer idFromFile : idList){
                    if (idFromFile >= id) id = idFromFile;
                }

                id = id + 1;


                //создадим строку используя форматирование строк.
                String string = String.format("\n%-8s%-30s%-8s%-4s", String.valueOf(id), productName, price, quantity);


                //теперь запишем строку в файл.
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(string);

                bufferedWriter.close();
                fileWriter.close();
            }
        }
        else{
            //тут ничего не делать. Программа запущена без параметров.
        }
    }
}
