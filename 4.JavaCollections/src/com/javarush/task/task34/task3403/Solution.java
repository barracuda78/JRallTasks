package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/

public class Solution {
//    public static void main(String[] args) {
//        new Solution().recurse(132);
//    }


    public void recurse(int n) {
        if(n == 1){
            //System.out.println(1);
            return;
        }
        if(n < 1){
            return;
        }

        //1. Пытаемся n разделить на i=2
        //2. Если удачно, то вызываем метод еще раз с аргументом n/i
        //3. Если не удачно то пытаемся n разделить на i++
        //
        //Осталось этот алгоритм ограничить и промежуточные результаты вывести в консоль.

        //запустил цикл от 2 до n, внутри проверял n%i == 0, вывод, рекурсия, break

        for (int i = 2; i <= n; i++){
            if(n%i == 0){
                System.out.print(i + " ");
                recurse(n/i);
                break;
            }
        }
    }
}
