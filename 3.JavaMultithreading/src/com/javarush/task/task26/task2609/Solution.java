package com.javarush.task.task26.task2609;

import java.util.concurrent.ConcurrentHashMap;

/*
Распределение элементов по корзинам с собственным локом
*/
public class Solution {
    private static final int NUMBER_LOCKS = 12;
    private final Node[] buckets;
    private final Object[] locks;

    static class Node {
        public Node next;
        public Object key;
        public Object value;
    }

    public Solution(int numberBuckets) {
        buckets = new Node[numberBuckets];
        locks = new Object[NUMBER_LOCKS];
        for (int i = 0; i < NUMBER_LOCKS; i++) {
            locks[i] = new Object();
        }
    }

    private final int hash(Object key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    //    В методе get() класса Solution в synchronized блоке используй lock из массива locks в зависимости от хэша объекта и количества лок объектов.
    public Object get(Object key) {
        int hash = hash(key);
        System.out.println("хэш = " + hash);
        synchronized (locks[hash % NUMBER_LOCKS]) {
            for (Node m = buckets[hash]; m != null; m = m.next) {
                if (m.key.equals(key)) return m.value;
            }
        }
        return null;
    }

    //В методе clear() класса Solution в synchronized блоке используй lock из массива locks в зависимости от индекса элемента и количества лок объектов.
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i % NUMBER_LOCKS]) {
                buckets[i] = null;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution(6);

        Solution.Node node0 = new Node();
        Solution.Node node1 = new Node();
        Solution.Node node2 = new Node();


        node0.key = "Zero";
        node0.value = "Зеро";
        node0.next = node1;

        node1.key = "One";
        node1.value = "Один";
        node1.next = node2;

        node2.key = "Two";
        node2.value = "Два";
        node2.next = null;

        String s = (String)solution.get("Zero");
        System.out.println(s);

    }
}