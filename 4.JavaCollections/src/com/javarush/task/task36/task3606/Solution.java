package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* 
Осваиваем ClassLoader и Reflection
*/
//Осваиваем ClassLoader и Reflection
//Аргументом для класса Solution является абсолютный путь к пакету.
//Имя пакета может содержать File.separator.
//В этом пакете кроме скомпилированных классов (.class) могут находиться и другие файлы (например: .java).
//Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
//Считай все классы с файловой системы, создай фабрику - реализуй метод getHiddenClassObjectByKey.
//Примечание: в пакете может быть только один класс, простое имя которого начинается с String key без учета регистра.
//
//
//Требования:
//1. Реализуй метод scanFileSystem, он должен добавлять в поле hiddenClasses найденные классы.
//2. Реализуй метод getHiddenClassObjectByKey, он должен создавать объект класса согласно условию задачи.
//3. Метод main не изменяй.
//4. Метод getHiddenClassObjectByKey не должен кидать исключений.
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        String sep = System.getProperty("file.separator");
        String pathName = packageName;
        if(!(packageName.endsWith(sep))){
            pathName = pathName.concat(sep);
        }
        File folder = new File(pathName);
        File[] listOfFiles = new File(pathName).listFiles();
        MyClassLoader classLoader = new MyClassLoader();
        for (File file:listOfFiles){
            if (file.getName().endsWith(".class")){
                hiddenClasses.add(classLoader.load(file.toPath()));
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class clazz:hiddenClasses){
            if(clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){
                Constructor[] constructors = clazz.getDeclaredConstructors();
                for(Constructor con : constructors){
                    if(con.getParameterTypes().length == 0){
                        con.setAccessible(true);
                        try {
                            return (HiddenClass)con.newInstance();
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    public class MyClassLoader extends ClassLoader {
        public Class<?> load(Path path) {
            try {
                byte[] b = Files.readAllBytes(path);
                return defineClass(null, b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

