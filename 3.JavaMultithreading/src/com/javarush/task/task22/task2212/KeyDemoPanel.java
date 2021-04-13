package com.javarush.task.task22.task2212;

import javax.swing.*;
import java.awt.*;


public class KeyDemoPanel extends JPanel {
    private Rectangle target = new Rectangle(420, 40, 60 , 30);
    private Rectangle mover = new Rectangle(200, 450, 15, 15);

    private Color targetColor = Color.BLUE;

    @Override
    public void paint(Graphics g){
        g.setColor(targetColor);
        g.fillRect(target.x, target.y, target.width, target.height);
        g.setColor(Color.ORANGE);
        g.fillRect(mover.x, mover.y, mover.width, mover.height);
    }

    public void moveLeft(){
        mover.x -= 5;
        this.repaint();
        checkIntersection();
    }

    public void moveRight(){
        mover.x += 5;
        this.repaint();
        checkIntersection();
    }

    public void moveUp(){
        mover.y -= 5;
        this.repaint();
        checkIntersection();
    }

    public void moveDown(){
        mover.y += 5;
        this.repaint();
        checkIntersection();
    }

    private void checkIntersection(){
        if(target.intersects(mover)){
            targetColor = Color.BLACK;
        }
        else{
            targetColor = Color.BLUE;
        }
    }
}
