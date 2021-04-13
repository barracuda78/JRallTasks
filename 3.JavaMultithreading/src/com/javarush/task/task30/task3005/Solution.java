package com.javarush.task.task30.task3005;

import java.util.ArrayList;
import java.util.List;

/* 
Такие хитрые исключения!
*/

public class Solution {
    public static void main(String[] args) {
        checkAFlag(new D());
        //checkAFlag(null);
    }

    public static void checkAFlag(D d) {
        //В методе checkAFlag должна быть проверка, что d.cs не null. Иначе вывод "Oops!".
        if(d == null ||
           d.cs == null ||
           d.cs.size() == 0 ||
           d.cs.get(0).bs == null ||
           d.cs.get(0).bs.size() == 0 ||
           d.cs.get(0).bs.get(0).as == null ||
           d.cs.get(0).bs.get(0).as.size() == 0){
            System.out.println("Oops!");
        }
        else if (d.cs.get(0).bs.get(0).as.get(0).flag) {
            System.out.println("A's flag is true");
        } else { //all other cases
            System.out.println("Oops!");
        }

//        if (d.cs.get(0).bs.get(0).as.get(0).flag) {
//            System.out.println("A's flag is true");
//        } else { //all other cases
//            System.out.println("Oops!");
//        }
    }

    static class A {
        boolean flag = true;
    }

    static class B {
        List<A> as = new ArrayList<>();
        //List<A> as ;

        {
            as.add(new A());
        }
    }

    static class C {
        List<B> bs = new ArrayList<>();
        //List<B> bs;

        {
            bs.add(new B());
        }
    }

    static class D {
        List<C> cs = new ArrayList<>();
        //List<C> cs ;

        {
            cs.add(new C());
        }
    }
}
