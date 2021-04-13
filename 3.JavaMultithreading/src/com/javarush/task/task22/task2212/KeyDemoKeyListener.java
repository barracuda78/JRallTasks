package com.javarush.task.task22.task2212;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyDemoKeyListener implements KeyListener {

    private KeyDemoPanel panel;

    public KeyDemoKeyListener(KeyDemoPanel panel){
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Напечатана клавиша " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Нажата клавиша " + e.getKeyChar());
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            panel.moveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            panel.moveRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_UP){
            panel.moveUp();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            panel.moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Отпушена клавиша " + e.getKeyChar());
    }
}
