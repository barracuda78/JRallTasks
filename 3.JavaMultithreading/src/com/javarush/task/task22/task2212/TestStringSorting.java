package com.javarush.task.task22.task2212;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;

public class TestStringSorting {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        //JPanel panel = new JPanel(new FlowLayout());
        KeyDemoPanel panel = new KeyDemoPanel();
        panel.setFocusable(true);
        panel.addKeyListener(new KeyDemoKeyListener(panel));
//        JTextField field = new JTextField(20);
//        JTextField field2 = new JTextField(10);
//        field.addKeyListener(new KeyDemoKeyListener(panel));
//
//        panel.add(field);
//        panel.add(field2);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}
