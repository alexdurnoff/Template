package ru.durnov.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EndOfWorkWindow extends Frame implements ActionListener {

    public EndOfWorkWindow(){
        setLayout(new FlowLayout());
        Button button = new Button("Ok");
        button.addActionListener(this);
        Label label = new Label("Генерация сметы завершена. Нажимаем ОК или закрываем окно");
        add(label);
        add(button);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(800,500);
        setBackground(Color.gray);
        setForeground(Color.BLUE);
        Font font = new Font("SansSerif", Font.PLAIN,16);
        setFont(font);
        setLocation(500, 500);
        setVisible(true);
    }

    public EndOfWorkWindow(String message){
        setLayout(new FlowLayout());
        Button button = new Button("Ok");
        button.addActionListener(this);
        Label label = new Label(message);
        add(label);
        add(button);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setSize(800,500);
        setBackground(Color.gray);
        setForeground(Color.BLUE);
        Font font = new Font("SansSerif", Font.PLAIN,16);
        setFont(font);
        setLocation(500, 500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.exit(0);
    }
}
