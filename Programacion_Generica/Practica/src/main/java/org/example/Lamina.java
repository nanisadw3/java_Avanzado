package org.example;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Lamina extends JPanel {
    public Lamina() {

        setLayout(new BorderLayout());
        JPanel panel = crearPanel();
        add(panel, BorderLayout.CENTER);
    }
    private JComboBox<String> tipoC = new JComboBox<>(new String[]{"String", "Integer", "Persona"});
    private JTextField valor = new JTextField(10);
    private JButton btn_crear = new JButton("Crear");
    private JTextArea salida = new JTextArea(10, 20);

    private JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                "Genericos", TitledBorder.LEFT, TitledBorder.TOP));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        btn_crear.addActionListener(e -> accionCrear());

        JLabel tipoLabel = new JLabel("Tipo:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(tipoLabel, gbc);
        gbc.gridx = 1;
        panel.add(tipoC, gbc);
        JLabel valorLabel = new JLabel("Valor:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(valorLabel, gbc);
        gbc.gridx = 1;
        panel.add(valor, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(btn_crear, gbc);
        JScrollPane scroll = new JScrollPane(salida);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(scroll, gbc);

        return panel;
    }
    private void accionCrear(){
        String tipo = (String) tipoC.getSelectedItem();
        String valorTexto = valor.getText();
        if(valorTexto.isEmpty()){
            JOptionPane.showMessageDialog(this, "El campo valor no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if(tipo.equals("String")){
                Generico<String> generico = new Generico<>();
                generico.setObjeto(valorTexto);
                salida.append(generico.toString() + "\n");
            }else if(tipo.equals("Integer")){
                try {
                    Integer valorInt = Integer.parseInt(valorTexto);
                    Generico<Integer> generico = new Generico<>();
                    generico.setObjeto(valorInt);
                    salida.append(generico.toString() + "\n");
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El valor no es un entero", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }else if(tipo.equals("Persona")){
                Generico<Persona> generico = new Generico<>();
                Persona persona = new Persona(valorTexto);
                generico.setObjeto(persona);
                salida.append(generico.toString() + "\n");
            }
        }
    }
}
class Generico<T>{
    private T objeto;
    public Generico(){
        this.objeto = objeto;
    }
    public void setObjeto(T objeto){
        this.objeto = objeto;
    }

    @Override
    public String toString() {
        return "Generico{" +
                "Objeto=" + objeto + "\n" +
                "Tipo=" + (objeto.getClass().getSimpleName()) +
                '}';
    }
}
class Persona{
    private String nombre;
    public Persona(String nombre){
        this.nombre = nombre;
    }
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
