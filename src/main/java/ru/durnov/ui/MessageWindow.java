package ru.durnov.ui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MessageWindow extends Frame {
    private String message;

    public MessageWindow(String message){
        this.message = message;
        setLayout(new FlowLayout());
        addWindowListener(new MyWindowAdapter(this));
        setTitle("Сообщение для пользователя");
        setSize(1000,200);
        setBackground(Color.gray);
        setForeground(Color.BLUE);
        Font font = new Font("SansSerif", Font.PLAIN,16);
        setFont(font);
        setLocation(500, 500);
        Label label = new Label(message);
        add(label);
        setVisible(true);
    }

    class MyWindowAdapter extends WindowAdapter{
        Frame parent;
        MyWindowAdapter(Frame parent){
            super();
            this.parent = parent;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            parent.dispose();
        }
    }


}
