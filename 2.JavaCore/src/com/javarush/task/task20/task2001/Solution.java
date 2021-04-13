package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            //File your_file_name = File.createTempFile("your_file_name", null);
            File your_file_name = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2001\\1");
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();
            outputStream.close();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();
            //check here that ivanov equals to somePerson - проверьте тут, что ivanov и somePerson равны

            //Мои проверки:

            //равны ли объекты класса Human?
            System.out.println(ivanov.equals(somePerson));
            //Что у меня попадает в somePerson?
            System.out.println(somePerson.toString());
            //записываю somePerson во второй файл для проверки
//            File file2 = new File("C:\\coding\\javarushtasks\\JavaRushTasks\\2.JavaCore\\src\\com\\javarush\\task\\task20\\task2001\\2");
//            OutputStream outputStream2 = new FileOutputStream(file2);
//            somePerson.save(outputStream2);
//            outputStream2.flush();
//            outputStream2.close();

            System.out.println(somePerson.name);
            System.out.println(somePerson.assets);
            System.out.println(ivanov.assets);

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        //мой код-------для тестирования переопределил метод toString для Human
        @Override
        public String toString(){
            String p = "";
            for(Asset asset : assets){
                p = "name: " + this.name + ", имущество: " +  asset.getName() + " цена: " + asset.getPrice();
            }
            return p;
        }

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);    //Создаем printWriter для записи в файл

            String isNamePresent = name!=null ? "yes" : "no";           //Создаем строковую переменную, чтобы понять, есть имя или нет.
            printWriter.println(isNamePresent);                         //Записываем эту строковую переменную в файл.
            printWriter.flush();                                        //Сливаем в файл все данные из буфера.
            if (name!=null) printWriter.println(name);

            String isAssetsExist = !assets.isEmpty() ? "yes" : "no";
            printWriter.println(isAssetsExist);
            printWriter.flush();
            //если !assets.isEmpty(), то
            //итерируемся по assets и вызываем в цикле метод asset.save() на каждом asset. При этом нужно прописать asset() метод в классе Asset.
            if (!assets.isEmpty()){
                int assetSize = assets.size();
                printWriter.println(assetSize); //запишем в файл число элементов листа assets
                printWriter.flush();
                for(Asset asset : assets){
                    asset.save(outputStream);
                }
            }


        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String isNamePresent = bufferedReader.readLine();
            if (isNamePresent.equals("yes"))
                name = bufferedReader.readLine();

            String isAssetsExist = bufferedReader.readLine();
            if (isAssetsExist.equals("yes")){
                int listSize = Integer.parseInt(bufferedReader.readLine());
                for(int i = 0; i < listSize; i++){
                    Asset asset = new Asset();
                    asset.load(inputStream);
                    assets.add(asset);
                }
            }
        }
    }
}

