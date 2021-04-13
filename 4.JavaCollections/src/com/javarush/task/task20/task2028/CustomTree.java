package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    //Queue<Entry<String>> list = new ArrayDeque<>();
    List<Entry<String>> list = new ArrayList<>();
    protected CustomTree() {
//        super();
        root = new Entry<>("root");
        list.add(root);
    }

//////////////////////////////////////////////////////////////////////////////////////////////////
    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren;
        boolean availableToAddRightChildren;
        Entry<T> parent;
        Entry<T> leftChild;
        Entry<T> rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }



        public boolean isAvailableToAddChildren(){
            return (availableToAddLeftChildren || availableToAddRightChildren);
        }
    }
///////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean add(String s) {
        for (Entry<String> item : list) {
                if (!item.isAvailableToAddChildren()){
                    continue;
                }
                root = new Entry<>(s);
                root.parent = item;
                if (item.availableToAddLeftChildren && item.availableToAddRightChildren){
                    item.leftChild = root;
                    list.add(root);
                    item.availableToAddLeftChildren = false;
                    return true;
                }
                else if(!item.availableToAddLeftChildren && item.availableToAddRightChildren){
                    item.rightChild = root;
                    list.add(root);
                    item.availableToAddRightChildren = false;
                    return true;
                }
        }
        return false;
    }

    public boolean remove(Object o){
        if(o instanceof String){
            String s = (String)o;
            ///////логика освобождения availableToAddLeftChildren или availableToAddRightChildren у родителя удаляемого объекта:
            //объект - удаляемый элемент:
            Entry<String> element = null;

            for(Entry<String> e : list){
                if(e.elementName.equals(s))
                    element = e;
            }

            //объект - родитель:
            Entry<String> parent = null;
            if(element != null){
                parent = element.parent;
            }

            //проверка, какую ветвь освобождать при удалении у родителя - правую или левую:
            if(parent != null){
                if(parent.leftChild == element)
                    parent.availableToAddLeftChildren = true;
                else if(parent.rightChild == element)
                    parent.availableToAddRightChildren = true;

            }
            /////////////////////удаление ветви:
            return removeChildren(s);
        }
        else{
            throw new UnsupportedOperationException();
        }
    }

    //вспомогательный метод: удаляет детей переданного имени элемента и сам элемент:
    private boolean removeChildren(String s){
        int sizeBefore = list.size();

        Entry<String> parent = null;

        for(Entry<String> e : list){
            if(e.elementName.equals(s))
                parent = e;
        }

        if(parent != null) {
            if (parent.leftChild != null) {
                removeChildren(parent.leftChild.elementName);
            }
            if (parent.rightChild != null) {
                removeChildren(parent.rightChild.elementName);
            }
            list.remove(parent);
        }

        return list.size() < sizeBefore;
    }

    //вспомогательный метод для тестирования - получить энтри:
    protected Entry<String> getEntry(String elementName){
        Entry<String> entry = null;
        for(Entry<String> e : list){
            if(e.elementName.equals(elementName)){
                entry = e;
            }
        }
        return entry;
    }


    public String getParent(String s){
        if(s.equals("root"))
            return "root";

        for (Entry<String> item : list
        ) {
            if (s.equals(item.elementName)){
                return item.parent.elementName;
            }
        }

        return null;
    }

    @Override
    public String get(int index) {
        //throw new UnsupportedOperationException();
        return list.get(index).elementName;

    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void add(int index, String element) {
        super.add(index, element);
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();

    }

    @Override
    public int indexOf(Object o) {
        return super.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return super.lastIndexOf(o);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Iterator<String> iterator() {
        return super.iterator();
    }

    @Override
    public int size() {
//        int i = 0;
//
//        for (Entry<String> item : list
//        ) {
//            i++;
//        }
//
//        return i ;
        return list.size() - 1;
    }

    @Override
    public ListIterator<String> listIterator() {
        return super.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return super.listIterator(index);
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        super.removeRange(fromIndex, toIndex);
        throw new UnsupportedOperationException();
    }
}