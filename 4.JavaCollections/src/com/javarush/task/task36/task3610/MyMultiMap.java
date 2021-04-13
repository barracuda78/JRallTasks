package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        //напишите тут ваш код
        int size = 0;
        if (this.map == null) return size;
        for (List element : this.map.values()) {
            size = size + element.size();
        }
        return size;
    }

    @Override
    public synchronized V put(K key, V value) {
        //напишите тут ваш код
        V lastAddedElement = null;
        if (map.containsKey(key)) {
            List<V> listOfValues = map.get(key);
            if (!listOfValues.isEmpty()) lastAddedElement = listOfValues.get(listOfValues.size() - 1);
            if (listOfValues.size() < repeatCount) {
                listOfValues.add(value);
            } else if (listOfValues.size() == repeatCount) {
                listOfValues.remove(0);
                listOfValues.add(value);
            }
        } else {
            List<V> newList = new ArrayList<>();
            newList.add(value);
            map.put(key, newList);
        }
        return lastAddedElement;
    }

    @Override
    public synchronized V remove(Object key) {
        //напишите тут ваш код
        V remove = null;
        if (!map.containsKey(key)) {
            return null;
        }
        if (map.get(key).size() >= 1) {
            remove = map.get(key).remove(0);
        }
        Iterator<Map.Entry<K, List<V>>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, List<V>> entry = iterator.next();
            if (entry.getValue().isEmpty()) {
                iterator.remove();
            }
        }
        return remove;
    }

    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return  map.keySet();
    }

    @Override
    public Collection<V> values() {
        //напишите тут ваш код
        ArrayList<V> list = new ArrayList<>();
        for (List l : map.values()) {
            list.addAll(l);
        }
        return list;
    }

    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        if (map.containsKey(key)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        //напишите тут ваш код
        for (V element : this.values()) {
            if (element.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}