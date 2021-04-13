package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {

        Date startDate = new Date();                        // Начало отсчёта
        Set set = getIds(shortener, strings);                   // Вызовем метод Get и преобразуем все строки в массив
        Date finishdate = new Date();                       // Завершение отсчёта
        ids.addAll(set);                                     // Такая конструкция нужна из-за того, что исходный поллученный по указтелю set после выхода из функции, пропадёт.
        Long delta = finishdate.getTime() - startDate.getTime();  // Подсчитаем разницу между началом и концом отрезка
        return delta;
    }
    public Set<Long> getIds(Shortener shortener, Set<String> strings) {
// Этот метод должен для переданного множества строк возвращать множество идентификаторов. Идентификатор для каждой отдельной строки нужно получить, используя shortener
        Set <Long> result = new HashSet<Long>(strings.size());  // Сделаем результирующий Set нужной длины
        Iterator<String> iterator = strings.iterator();         // Cоздадим итератор
        while (iterator.hasNext()) {
            result.add( shortener.getId(iterator.next()));  // Try не нужен т.к. в случае, если нет элемента, то он будет добавлен
        }

        return result;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date startDate = new Date();                        // Начало отсчёта
        Set set = getStrings(shortener, ids);            // Вызовем метод Get и преобразуем все строки в массив
        Date finishdate = new Date();                       // Завершение отсчёта
        strings.addAll(set);
        Long delta = finishdate.getTime() - startDate.getTime();  // Подсчитаем разницу между началом и концом отрезка
        return delta;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
// Метод будет возвращать множество строк, которое соответствует переданному множеству идентификаторов.
        Set <String > result = new HashSet<String>(keys.size());  // Сделаем результирующий Set нужной длины
        Iterator<Long> iterator = keys.iterator();         // Cоздадим итератор
        while (iterator.hasNext()) {
            String sTmp = shortener.getString(iterator.next());
            if (sTmp != null)
                result.add(sTmp);  // Если строки по ID не было, то мы пустышку добавлять не будем
        }

        return result;
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids = new HashSet<>();
        Long timeWhithHashMapIds= getTimeToGetIds(shortener1, origStrings, ids);
        Long timeWhithHashMapStrings = getTimeToGetStrings(shortener1,ids,new HashSet<String>());

        Set<Long> idsBi = new HashSet<>();
        Long timeWhithHashBiMapIds = getTimeToGetIds(shortener2, origStrings,idsBi);
        Long timeWhithHashBiMapStrings = getTimeToGetStrings(shortener2,idsBi,new HashSet<String>());

        Assert.assertTrue(timeWhithHashMapIds > timeWhithHashBiMapIds);
        Assert.assertEquals(timeWhithHashMapStrings, timeWhithHashBiMapStrings,30);
    }
}
