package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

//    @Override
//    public Object clone(){
//        Solution solution = new Solution("1", "2");
//        return solution;
//    }

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        //if( o == null || o.getClass() != getClass()) return false;
        if( o == null) return false;
        if( o.getClass() != getClass()) return false;




        if(!(o instanceof Solution)) return false;

        Solution solution = (Solution) o;

        //if(first != null ? !first.equals(solution.first) : solution.first != null) return false;
        //if(!Objects.equals(first, solution.first)) return false;
        //if(last != null ? !last.equals(solution.last) : solution.last != null) return false;
        //if(!Objects.equals(last, solution.last)) return false;


        return Objects.equals(first, solution.first) && Objects.equals(last, solution.last);


        //return true;
    }

    public int hashCode() {
        return Objects.hash(first, last);
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
