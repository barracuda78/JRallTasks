package tsystems;

//System.out.println(c.evaluate("(1+38)*4-5")); // Result: 151
//System.out.println(c.evaluate("7*6/2+8")); // Result: 29
//System.out.println(c.evaluate("-12)1//(")); // Result: null

public class Solution {
    public static void main(String[] args) {
        Calculator cl = new Calculator();
        String answer = cl.evaluate("8-(-2-3)*5+(5-6*(-1-9))*2-1");
        System.out.println("Result of the statement is: " + answer);
    }
}
