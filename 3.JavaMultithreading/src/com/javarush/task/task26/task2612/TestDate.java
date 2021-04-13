package com.javarush.task.task26.task2612;



import javax.swing.*;
import java.awt.*;


public class TestDate {

    public static JFrame getJFrame(){
        JFrame frame = new JFrame("barracuda's frame");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new MyComponent());

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        frame.setBounds(dimension.width/3, dimension.height/3, dimension.width/2, dimension.height/2);

        JTextField textField = new JTextField(50);
        textField.setVisible(true);
        textField.setEditable(true);
        frame.getContentPane().add(textField, BorderLayout.NORTH);

        JTextArea messages = new JTextArea(10, 50);
        messages.setEditable(false);
        frame.getContentPane().add(new JScrollPane(messages), BorderLayout.WEST);

        JTextArea users = new JTextArea(10, 10);
        users.setEditable(false);
        frame.getContentPane().add(new JScrollPane(users), BorderLayout.EAST);

        JButton button = new JButton("OK");
        frame.getContentPane().add(button);

        frame.pack();

        return frame;
    }

    public static class MyComponent extends JComponent{
        protected void paintComponent(Graphics g){

        }
    }


    public static void main(String[] args) {
        JFrame jFrame = getJFrame();
    }

}
