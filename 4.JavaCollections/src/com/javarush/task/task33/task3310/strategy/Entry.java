package com.javarush.task.task33.task3310.strategy;

import java.io.Serializable;
import java.util.Objects;

//Shortener (7)
//Приступим к реализации второй стратегии OurHashMapStorageStrategy. Она не будет использовать готовый HashMap из стандартной библиотеки, а будет сама являться коллекцией.
//
//7.1. Разберись как работает стандартный HashMap, посмотри его исходники или погугли статьи на эту тему.
//7.2. Если ты честно выполнил предыдущий пункт, то ты знаешь для чего используется класс Node поддерживающий интерфейс Entry внутри HashMap. Создай свой аналог внутри пакета strategy. Это должен быть обычный, не вложенный, не generic класс. Сделай его публичным.
//В отличии от класса Node из HashMap, наш класс будет поддерживать только интерфейс Serializable и будет называться Entry.
//7.3. Добавь в Entry следующие поля: Long key, String value, Entry next, int hash. Как видишь, наша реализация будет поддерживать только тип Long для ключа и только String для значения. Область видимости полей оставь по умолчанию.
//7.4. Добавь и реализуй конструктор Entry(int hash, Long key, String value, Entry next).
//7.5. Добавь и реализуй методы: Long getKey(), String getValue(), int hashCode(), boolean equals() и String toString(). Реализовывать остальные методы оригинального Entry не нужно, мы пишем упрощенную версию.
//
//
//Требования:
//1. В классе Entry должны быть созданы поля перечисленные в условии задачи.
//2. В классе Entry должен быть реализован публичный конструктор с четырьмя параметрами (int, Long, String, Entry) инициализирующий соответствующие поля класса.
//3. Метод getKey должен возвращать значение поля key.
//4. Метод getValue должен возвращать значение поля value.
//5. Метод toString должен возвращать строку формата key + "=" + value.
//6. Методы hashCode и equals должны быть корректно реализованы используя для сравнения поля key и value.
public class Entry implements Serializable {

    Long key;
    String value;
    Entry next;
    int hash;

    public Entry(int hash, Long key, String value, Entry next) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.hash = hash;
    }

    public final Long getKey() {
        return key;
    }

    public final String getValue() {
        return value;
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Entry entry = (Entry) o;
//
//        if (key != null ? !key.equals(entry.key) : entry.key != null) return false;
//        return value != null ? value.equals(entry.value) : entry.value == null;
//    }
//
//    public final int hashCode() {
//        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(key, entry.key) &&
                Objects.equals(value, entry.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key, value);
    }


}
