package com.javarush.task.task21.task2102;

public class FactoryTest {




    public static void main(String[] args) {
//        PizzaFactory pizzaFactory = new PizzaFactory();
//        //pizzaFactory.createPizza(PizzaType.MARGARITA);
//        PizzaShop pizzaShop = new PizzaShop(pizzaFactory);
//        pizzaShop.orderPizza(PizzaType.MARGARITA);
//        pizzaShop.orderPizza(PizzaType.FOURCHEESES);
//        pizzaShop.orderPizza(PizzaType.BACONPIZZA);

        Class<Pizza> c = Pizza.class;
        int classmodifiers = Pizza.class.getModifiers();
        System.out.println(classmodifiers);
        System.out.println(Integer.toBinaryString(classmodifiers));
    }

    public static  class Pizza{
        public void makePizza(){
            System.out.println("We are making a pizza");
        }
        public void bakePizza(){
            System.out.println("We are baking a pizza");
        }
        public void saltPizza(){
            System.out.println("We are salting a pizza");
        }
    }

    public static  class Margarita extends Pizza{
        @Override
        public void makePizza(){
            System.out.println("We are making a pizza margarita");
        }
        @Override
        public void bakePizza(){
            System.out.println("We are baking a pizza margarita");
        }
        @Override
        public void saltPizza(){
            System.out.println("We are salting a pizza margarita");
        }

    }
    public static  class FourCheeses extends Pizza{

    }
    public static class BaconPizza extends Pizza{

    }


    public static class PizzaFactory{
        public Pizza createPizza(PizzaType pizzaType){
            Pizza pizza = null;
            switch(pizzaType){
                case MARGARITA:
                    pizza = new Margarita();
                    break;
                case FOURCHEESES:
                    pizza = new FourCheeses();
                    break;
                case BACONPIZZA:
                    pizza = new BaconPizza();
            }

            return pizza;
        }
    }

    public static class PizzaShop{
        private final PizzaFactory pizzaFactory;

        public PizzaShop (PizzaFactory pizzaFactory){
            this.pizzaFactory = pizzaFactory;
        }

        public Pizza orderPizza(PizzaType type){
            Pizza pizza = pizzaFactory.createPizza(type);
            pizza.makePizza();
            pizza.bakePizza();
            pizza.saltPizza();
            System.out.println("Here you are your pizza "  + type + "!!!");
            return pizza;
        }

    }

    public enum PizzaType{
        MARGARITA,
        FOURCHEESES,
        BACONPIZZA
    }


}
