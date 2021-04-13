package com.javarush.task.task17.task1713;

public class Test {
/*    public static void main(String[] args) {
        A a = new A();
        a.f();
        B b = new B();
        b.f();
    }

static class A{
    void f(){
        System.out.println("I'm from A class");
    }
}

static class B{
    A a = new A();
    void f(){
        a.f();
    }
}*/

interface Graphics{
    void draw();
}

    static class Triangle implements Graphics{
        public void draw(){
            System.out.println("рисую треугольник");
        }
    }

    static class Square implements Graphics{
        public void draw(){
            System.out.println("рисую квадрат");
        }
    }

    static class Circle implements Graphics{
        public void draw(){
            System.out.println("рисую окружность");
        }
    }

    static class Painter{
    Graphics graphics;

    void setGraphics(Graphics g){
        graphics = g;
    }

    void draw(){
        graphics.draw();
    }
    }

    public static void main(String[] args) {
        Painter painter01 = new Painter();
        painter01.setGraphics(new Triangle());
        painter01.draw();
        Painter painter02 = new Painter();
        painter02.setGraphics(new Square());
        painter02.draw();
        Painter painter03 = new Painter();
        painter03.setGraphics(new Circle());
        painter03.draw();
    }
}