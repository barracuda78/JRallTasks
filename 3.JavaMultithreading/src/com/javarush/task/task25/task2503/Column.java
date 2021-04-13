package com.javarush.task.task25.task2503;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public enum Column implements Columnable{
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();
        for(int i = 0; i < values().length; i++){
            result.add(null);
        }

        for(int i = 0; i < values().length; i++){
            if(realOrder[i] == -1) continue;
            //realOrder contains of 4 units:
            // cust  bank    acc   amount ------>  это наисенование колонок в исходном enum ( с исходным порядком колонок,т.е. элементов enum'а).
            //  0      1      2      3    ------>  это индекс (i) в массиве enum (получаем массив через Column.values()), т.е. это номер колонки в  исходном енаме.
            //[-1 ]  [ 2 ]  [ 1 ]  [ 0 ]  ------>  this is an element of realOrder[i] - это желаемый порядок колонок (т.е. элементов enum'а) для вывода на экран.
            //how to get the Column column from enm by index in enum itself? Create  array witn .values()
            //Column.configureColumns(Column.Amount, Column.AccountNumber, Column.BankName);
            //Column.AccountNumber.hide();
            Column[] array = Column.values();
            //в result должны попасть значения:
            //в позицию 0:
            result.set(realOrder[i], array[i]);
            //array:
            //   0            1             3           4
            //  customer     bank        account      amount
        }

        result.removeAll(Collections.singleton(null));

        return result;
    }

    //@return полное имя колонки
    //6. Метод Column.getColumnName должен возвращать полное имя колонки.
    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public boolean isShown() {
        if(realOrder[ordinal()] >=0 ){
            return true;
        }
        return false;
    }

    @Override
    public void hide() {
        realOrder[ordinal()] = -1;
    }
}

