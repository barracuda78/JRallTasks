package com.javarush.task.task34.task3413;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SoftCache {
    private Map<Long, SoftReference<AnyObject>> cacheMap = new ConcurrentHashMap<>();

    public AnyObject get(Long key) {
        if(!cacheMap.containsKey(key)){
            return null;
        }
        SoftReference<AnyObject> softReference = cacheMap.get(key);
        return softReference.get();
        //напишите тут ваш код
    }

    //Метод AnyObject put(Long key, AnyObject value) должен добавлять в мапу пару key : value.
    // Метод должен вернуть null, если в cacheMap по такому ключу ранее не было значения.
    // Иначе - верни предыдущее значение value по этому ключу.
    // Не забудь вызвать метод clear() у объекта типа SoftReference<AnyObject>.
    public AnyObject put(Long key, AnyObject value) {
        boolean containsKey = cacheMap.containsKey(key);
        AnyObject previous = null;
        if(containsKey)
            previous = cacheMap.get(key).get();

        SoftReference<AnyObject> softReference = cacheMap.put(key, new SoftReference<>(value));
        if (!containsKey)
            return null;
        softReference.clear();
        return previous;
        //напишите тут ваш код
    }

    //Метод AnyObject remove(Long key) должен удалить из мапы cacheMap пару key : value по ключу key.
    // Метод должен вернуть null, если в cacheMap по такому ключу ранее не было значения.
    // Иначе - верни предыдущее значение value по этому ключу.
    // Не забудь вызвать метод clear() у объекта типа SoftReference<AnyObject>.
    public AnyObject remove(Long key) {

        SoftReference<AnyObject> softReference = cacheMap.remove(key);

        AnyObject o = softReference == null ? null : softReference.get();

        if(softReference != null)
            softReference.clear();

        return o;

        //напишите тут ваш код
    }
}