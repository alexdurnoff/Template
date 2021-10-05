package ru.durnov.ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExceptionWindow extends Frame {
    private String message;

    public ExceptionWindow(String message){
        this.message = message;
        setLayout(new FlowLayout());
        addWindowListener(new MyWindowAdapter(this));
        setTitle("Сообщение для пользователя");
        setSize(1000,800);
        setBackground(Color.gray);
        setForeground(Color.BLUE);
        Font font = new Font("SansSerif", Font.PLAIN,16);
        setFont(font);
        setLocation(10, 10);
        Label label = new Label(message);
        add(label);
        setVisible(true);
    }

    class MyWindowAdapter extends WindowAdapter{
        MyWindowAdapter(Frame parent){
            super();
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }


}
