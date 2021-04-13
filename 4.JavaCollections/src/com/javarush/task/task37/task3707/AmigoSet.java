package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;
//AmigoSet (6)
//Открой исходники HashSet (если у тебя нет исходников джавы, то скачай их и подключи), сравни со своим кодом.
//Быстро это можно сделать сравнив через буфер. Скопируй код класса HashSet в буфер.
//Зайди в класс AmigoSet, далее правая кнопка мыши -> Compare with Clipboard.
//
//Ты только что реализовал сет, аналогичный HashSet. Теперь будешь знать, как внутри устроен HashSet.
//Молодец, теперь коллекции тебе не страшны!
//
//
//Требования:
//1. Поздравляю, ты написал собственную реализацию множества и изучил HashSet во всех деталях!
public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final long serialVersionUID = 1L;
    private static Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = 0;
        //Вычисли свою Capacity по такой формуле:
        // максимальное из 16 и округленного в большую сторону значения (collection.size()/.75f)
        int collectionSize = (int)(Math.ceil(collection.size()/.75f));


        capacity = 16 > collectionSize ? 16 : collectionSize;

        map = new HashMap<>(capacity);

        this.addAll(collection);
    }

    @Override
    public boolean add(E e){
        return null== map.put(e, PRESENT);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty(){
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o){
        return map.containsKey(o);
    }

    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public boolean remove(Object o){
        return null == map.remove(o);
    }

    @Override
    public Object clone() {
        AmigoSet<E> amigoSet = new AmigoSet<>();
//        amigoSet.map = new HashMap<>(map.size());
//        for(Map.Entry<E, Object> pair : map.entrySet()){
//            amigoSet.map.put(pair.getKey(), pair.getValue());
//        }
        try {
            amigoSet.map = (HashMap<E, Object>)(map.clone());
        }
        catch(Exception e){
            throw new InternalError();
        }
        return amigoSet;
    }

//    private Object readResolve() throws ObjectStreamException{
//
//    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        Integer capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
        Float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");
        Integer size = map.size();
        oos.writeObject(capacity);
        oos.writeObject(loadFactor);
        oos.writeObject(size);
        for(Map.Entry<E, Object> pair : map.entrySet()){
            oos.writeObject(pair.getKey());
        }
        oos.flush();

    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        Integer capacity = (Integer)ois.readObject();
        Float loadFactor = (Float)ois.readObject();
        Integer size = (Integer)ois.readObject();

        HashMap<E, Object> map = new HashMap<>(capacity, loadFactor);
        for(int i = 0; i < size; i++){
            map.put((E)ois.readObject(), PRESENT);
        }

        this.map = map;
    }

}
//код для проверки:


//class Solution {
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        HashSet<String> hashSet = new HashSet<>();
//        hashSet.add("ddd");
//        hashSet.add("rrrr");
//        AmigoSet amigoSet = new AmigoSet(hashSet);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ObjectOutputStream objectOutputStream =
//                new ObjectOutputStream(byteArrayOutputStream);
//        objectOutputStream.writeObject(amigoSet);
//        objectOutputStream.close();
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
//        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
//        AmigoSet amigoSet1 = (AmigoSet)  objectInputStream.readObject();
//        System.out.println(amigoSet.equals(amigoSet1));
//        System.out.println(amigoSet);
//        System.out.println("________");
//        System.out.println(amigoSet1);
//    }
//}
