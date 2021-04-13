package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;
import java.util.Map;

public class OurHashBiMapStorageStrategy implements StorageStrategy {
    HashMap<Long, String> k2v = new HashMap<>();
    HashMap<String, Long> v2k = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        if (k2v.containsKey(key)) {
            return true;
        } else
            return false;
    }

    @Override
    public boolean containsValue(String value)  {
        if (v2k.containsKey(value)) {
            return true;
        } else
            return false;

    }

    @Override
    public void put(Long key, String value)  {
        v2k.put(value, key);
        k2v.put(key, value);
    }

    @Override
    public Long getKey(String value)  {
        Long result = null;
//        for (Map.Entry<String,Long> l : v2k.entrySet()) {
//            if (l.getKey().equals(value)) {
//                result = l.getValue();
//            }
//        }
        result = v2k.get(value);
        return result;
    }

    @Override
    public String getValue(Long key)  {
        return k2v.get(key);
    }
}