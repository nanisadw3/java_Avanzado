package org.example;

import javax.swing.*;

public class Marco extends JFrame {
    Marco() {
        setTitle("Practica");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        Lamina lamina = new Lamina();
        add(lamina);
    }
}
