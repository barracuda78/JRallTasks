package com.javarush.task.task20.task2028;

import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<String> list = new CustomTree();

        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }

        System.out.println("The list size is " + list.size());
        System.out.println("The expected parent is 3. The actual parent is " + ((CustomTree) list).getParent("8"));
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("20"));

        list.remove("3");
        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("8"));

        list.add("16");
        System.out.println("The expected parent is 9. The actual parent is " + ((CustomTree) list).getParent("16"));

        list.remove("4");
        list.remove("5");
        list.remove("6");
        System.out.println("Expected: true. Actual: " + list.add("20"));
        System.out.println("The expected parent is 1. The actual parent is " + ((CustomTree) list).getParent("20"));
    }

    //my main for testing:
//    public static void main(String[] args) {
//        List<String> list = new CustomTree();
//
//        for (int i = 1; i < 16; i++) {
//            list.add(String.valueOf(i));
//            System.out.println("Цикл " + i + " , добавил элемент.");
//        }
//
//        System.out.println("The list size is " + list.size());
//        System.out.println("The expected parent is 3. The actual parent is " + ((CustomTree) list).getParent("8"));
//        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("20"));
//
//        System.out.println("===============REMOVE: =====================");
//        System.out.println("=============Список элементов до удаления ТРОЙКИ:: =============");
//        for(int i = 1; i <= list.size(); i++){
//            System.out.println(list.get(i));
//        }
//
//        System.out.println("=============Список элементов и их дети до удаления ТРОЙКИ:: =============");
//        for(int i = 1; i <= list.size(); i++){
//            System.out.println("Родитель: " + ((CustomTree) list).getParent(list.get(i)) + ", Элемент: " + list.get(i));
//        }
//
//        System.out.println("\n Удалена тройка: " + list.remove("3"));
//        //проверить статус возможности добавления чайлдов к единице:
//        CustomTree.Entry<String> oneEntry = ((CustomTree) list).getEntry("1");
//        System.out.println("Элемент '1' способен добавить leftChild: " + oneEntry.availableToAddLeftChildren);
//        System.out.println("Элемент '1' способен добавить rightChild: " + oneEntry.availableToAddRightChildren);
//
//
//        System.out.println("=============Список элементов ПОСЛЕ УДАЛЕНИЯ ТРОЙКИ: =============");
//        for(int i = 1; i <= list.size(); i++){
//            System.out.println(list.get(i));
//        }
//        System.out.println("=============Список элементов и их дети ПОСЛЕ УДАЛЕНИЯ ТРОЙКИ: =============");
//        for(int i = 1; i <= list.size(); i++){
//            System.out.println("Родитель: " + ((CustomTree) list).getParent(list.get(i)) + ", Элемент: " + list.get(i));
//            CustomTree.Entry<String> e = ((CustomTree) list).getEntry(list.get(i));
//            System.out.println("     способен добавить налево: " + e.availableToAddLeftChildren);
//            System.out.println("     способен добавить направо: " + e.availableToAddRightChildren);
//        }
//        System.out.println("The expected parent is null. The actual parent is " + ((CustomTree) list).getParent("8"));
//
//        System.out.println("\nДобавлен элемент 16: " + list.add("16"));
//        System.out.println();
//
//        System.out.println("The expected parent is 9. The actual parent is " + ((CustomTree) list).getParent("16"));
//
//        System.out.println("=============Список элементов и их дети ПОСЛЕ ДОБАВЛЕНИЯ 16: =============");
//        for(int i = 1; i <= list.size(); i++){
//            System.out.println("Родитель: " + ((CustomTree) list).getParent(list.get(i)) + ", Элемент: " + list.get(i));
//        }

//        System.out.println("\n удалены элементы 4, 5, 6. \n");
//        list.remove("4");
//        list.remove("5");
//        list.remove("6");
//        System.out.println("Expected: true. Actual: " + list.add("20"));
//        System.out.println("The expected parent is 1. The actual parent is " + ((CustomTree) list).getParent("20"));
//
//        System.out.println("=============Список элементов ПОСЛЕ ДОБАВЛЕНИЯ 20: =============");
//        for(int i = 1; i <= list.size(); i++){
//            System.out.println(list.get(i));
//        }
//        System.out.println("=============Список элементов и их дети ПОСЛЕ ДОБАВЛЕНИЯ 20: =============");
//        for(int i = 1; i <= list.size(); i++){
//            System.out.println("Родитель: " + ((CustomTree) list).getParent(list.get(i)) + ", Элемент: " + list.get(i));
//        }
//
//    }
}
