package com.javarush.task.task33.task3310.strategy;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;



public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap<Long, String> data = new DualHashBidiMap<>();

    @Override
    // должен вернуть true, если хранилище содержит переданный ключ.
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    // должен вернуть true, если хранилище содержит переданное значение.
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    // добавить в хранилище новую пару ключ - значение.
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    // вернуть ключ для переданного значения.
    public Long getKey(String value) {
        return data.getKey(value);
    }

    @Override
    // вернуть значение для переданного ключа.
    public String getValue(Long key) {
        return data.get(key);
    }
}
