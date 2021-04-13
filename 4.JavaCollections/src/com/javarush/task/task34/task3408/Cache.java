package com.javarush.task.task34.task3408;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

public class Cache<K, V> {
    private Map<K, V> cache = new WeakHashMap<>();   //TODO add your code here

    public V getByKey(K key, Class<V> clazz) throws Exception {
        //TODO add your code here
        //2. Реализуй логику метода getByKey:
        //2.1. Верни объект из cache для ключа key.
        //2.2. Если объекта не существует в кэше,
        // то добавьте в кэш новый экземпляр используя рефлексию, см. пункт а).
        //а) публичный конструктор с одним параметром типа K
        if(cache.containsKey(key)){
            return cache.get(key);
        }else{
            //    Constructor<?> constructor = ConstructorUtils.getMatchingAccessibleConstructor(input, parameter.getClass());
            //    return (T) constructor.newInstance(parameter);
            Class[] parameterType = new Class[1];
            parameterType[0] = key.getClass();
            Constructor<V> constructor = clazz.getDeclaredConstructor(parameterType);
           // V v = clazz.newInstance();
            V v = constructor.newInstance(key);

            cache.put(key, v);
            return v;
        }
    }

    //3. Реализуй логику метода put:
    //3.1. Используя рефлексию получи ссылку на метод, описанный в пункте б).
    //б) метод K getKey() с любым модификатором доступа.
    //3.2. Используя рефлексию разреши к нему доступ.
    //3.3. Используя рефлексию вызови метод getKey у объекта obj, таким образом ты получишь ключ key.
    //3.4. Добавь в кэш пару <key, obj>.
    //3.5. Верни true, если метод отработал корректно, false в противном случае. Исключения игнорируй.
    public boolean put(V obj) {
        //TODO add your code here
        try {
            Method method = obj.getClass().getDeclaredMethod("getKey", new Class[0] );
            method.setAccessible(true);
            K key = (K)(method.invoke(obj, new Class[0]));
            cache.put(key, obj);
            return true;
        } catch (NoSuchMethodException e) {

        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
        return false;
    }

    public int size() {
        return cache.size();
    }
}
