package com.javarush.task.task19.task1922;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        try {
            //читаем с консоли имя файла:
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = bufferedReader.readLine();
            bufferedReader.close();

            //создадим счетчик слов.


            //создадим мапу, в которую помещаем строку и ее счетчик. Или список типа Mystring
            //Map<String, Integer> map = new HashMap<>();
            List<Mystring> list = new ArrayList<>();

            //прочитаем файл
            BufferedReader bufferedReader1 = new BufferedReader(new FileReader(fileName));
            while(bufferedReader1.ready()){
                String s = bufferedReader1.readLine();
                String[] sArray = s.split(" ");

                //сделаем два вложенных цикла - итерируемся по sArray и внутренний - по words. Если значения совпадают - инкрементируем counter, записываем в мапу s + counter
                int counter = 0;
                for(int i = 0; i < sArray.length; i++){

                    for(int j = 0; j < words.size(); j++){
                        if(sArray[i].equals(words.get(j))){
                            counter++;
                        }
                    }

                }
                Mystring mystring = new Mystring(s, counter);
                list.add(mystring);
                //map.put(s, counter);
            }
            bufferedReader1.close();

            //итерируемся по списку. Если value ==2 -> выводим key в консоль.
            for(Mystring mystring : list){
                Mystring ms = mystring;
                String s = ms.string;
                int cnt = ms.count;
                //System.out.println(s + " : " + cnt);     //------------------------->COMMENT BEFORE VALIDATING
                if(cnt == 2) System.out.println(s);    //------------------------->UNCOMMENT BEFORE VALIDATING
            }

        }
        catch(IOException i){
            i.printStackTrace();
        }
    }

    static class Mystring{

        public String string;
        public int count;

        public Mystring(String string, int count){
            this.string = string;
            this.count = count;
        }
        public String toString(){
            return string;
        }
    }
}
