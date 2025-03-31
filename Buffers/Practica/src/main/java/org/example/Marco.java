package org.example;

import javax.swing.*;

public class Marco extends JFrame {
    public Marco() {
        setTitle("Practica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        Lamina lamina = new Lamina();
        add(lamina);
    }
}
