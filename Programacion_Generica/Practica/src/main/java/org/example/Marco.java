package org.example;

import javax.swing.*;

public class Marco extends JFrame {
    public Marco(){
        setTitle("Practica de genericos");
        setSize(300, 350);
        Lamina l = new Lamina();
        add(l);
    }
}
