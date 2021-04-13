package com.javarush.task.task17.task1721;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/* Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла.
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines.
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
Не забудь закрыть потоки.

Требования:
1. Класс Solution должен содержать public static поле allLines типа List<String>.
2. Класс Solution должен содержать public static поле forRemoveLines типа List<String>.
3. Класс Solution должен содержать public void метод joinData() который может бросать исключение CorruptedDataException.
4. Программа должна считывать c консоли имена двух файлов.
5. Программа должна считывать построчно данные из первого файла в список allLines.
6. Программа должна считывать построчно данные из второго файла в список forRemoveLines.
7. Метод joinData должен удалить в списке allLines все строки из списка forRemoveLines, если в allLines содержаться все строки из списка forRemoveLines.
8. Метод joinData должен очистить список allLines и выбросить исключение CorruptedDataException, если в allLines не содержаться все строки из списка forRemoveLines.
9. Метод joinData должен вызываться в main.
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void scanLines(String name, List<String> lines)  {
        File file1 = new File(name);
        Scanner scanner = null;

        try {
            scanner = new Scanner(file1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(scanner.hasNextLine()){
            String s = scanner.nextLine();
            lines.add(s);
        }
    }

    public static void main(String[] args) {



        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name1 = bufferedReader.readLine();
            String name2 = bufferedReader.readLine();
            scanLines(name1, allLines);
            scanLines(name2, forRemoveLines);
        }
        catch(IOException i){
            i.printStackTrace();
        }
        try {
            (new Solution()).joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        }

    }

/*
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines.
4. Если условие из п.3 не выполнено, то:
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
*/
    public void joinData() throws CorruptedDataException {
        int count = 0;
        for(int i = 0; i < allLines.size(); i++){
            for(int j = 0; j < forRemoveLines.size(); j++){
                if(allLines.get(i).equals(forRemoveLines.get(j))){
                    count++;
                }
            }
        }
        if(count >= forRemoveLines.size()){
            for(int i = 0; i < allLines.size(); i++){
                for(int j = 0; j < forRemoveLines.size(); j++){
                    if(allLines.get(i).equals(forRemoveLines.get(j))){
                        allLines.remove(i);
                    }
                }
            }
        }
        else{
            allLines.removeAll(allLines);
            throw new CorruptedDataException();
        }
    }
}
