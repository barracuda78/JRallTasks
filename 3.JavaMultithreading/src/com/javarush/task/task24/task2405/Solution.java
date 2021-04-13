package com.javarush.task.task24.task2405;

/* 
Black box
*/
public class Solution implements Action {
    public static int countActionObjects;

    private int param;

    private Action solutionAction = new Action() {
        //напишите тут ваш код

        public void someAction() {

            if(param >0){
                //этот цикл дает строку 54321 в столбик.
                for(int i = param; i > 0; i--){
                    System.out.println(param);
                    param--;
                }
                //создание этого объекта дает countActionObjects++
                new FirstClass(){
                    @Override
                    public Action getDependantAction() {
                        return new SecondClass();
                        //return null;
                    }
                }.someAction();
                new SecondClass(){
                    @Override
                    public void someAction(){
                        super.someAction();
                        char[] sArray = SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM.toCharArray();
                        char[] newArray = new char[sArray.length-1];
                        for(int i = 1; i < sArray.length; i++){
                            newArray[i-1] = sArray[i];
                        }
                        String s = new String(newArray);
                        System.out.println(s + param);
                    }
                }.someAction();


            } else{
                //создание этого объекта дает countActionObjects++
                new SecondClass(){
                    @Override
                    public void someAction(){
                        super.someAction();
                        char[] sArray = SPECIFIC_ACTION_FOR_ANONYMOUS_SECOND_CLASS_PARAM.toCharArray();
                        char[] newArray = new char[sArray.length-1];
                        for(int i = 1; i < sArray.length; i++){
                            newArray[i-1] = sArray[i];
                        }
                        String s = new String(newArray);
                        System.out.println(s + param);
                    }
                }.someAction();
            }
            //напишите тут ваш код
        }
    };


    public Solution(int param) {
        this.param = param;
    }

    @Override
    public void someAction() {
        solutionAction.someAction();
    }

    /**
     * 5
     * 4
     * 3
     * 2
     * 1
     * class FirstClass, method someAction
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = 0
     * Count of created Action objects is 2
     * class SecondClass, method someAction
     * Specific action for anonymous SecondClass, param = -1
     * Count of created Action objects is 3
     */
    public static void main(String[] args) {
        Solution solution = new Solution(5);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);

        solution = new Solution(-1);
        solution.someAction();
        System.out.println("Count of created Action objects is " + countActionObjects);
    }
}
