import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lamina extends JPanel {
    public Lamina() {
        setLayout(new BorderLayout());

        JPanel panel = lamina_arriba();
        add(panel, BorderLayout.CENTER);

        panel = lamina_tabla();
        add(panel, BorderLayout.SOUTH);
    }

    JTextField txt_sueldo;
    JTextField txt_nombre;
    JTextField txt_fecha;

    private JPanel lamina_arriba() {
        JPanel lamina = new JPanel();
        lamina.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel nombreLabel = new JLabel("Nombre:");
        txt_nombre = new JTextField(10);
        JLabel sueldoLabel = new JLabel("Sueldo:");
        txt_sueldo = new JTextField(10);
        JLabel fecha = new JLabel("Fecha:");
        txt_fecha = new JTextField(10);
        JButton btn_Agregar = new JButton("Agregar");
        JButton btn_Guardar = new JButton("Guardar");
        JButton btn_Cargar = new JButton("Cargar");
        btn_Agregar.addActionListener(e -> accion_Agregar());
        btn_Guardar.addActionListener(e -> accion_Guardar());
        btn_Cargar.addActionListener(e -> accion_Cargar());

        gbc.gridy = 0; gbc.gridx = 0; lamina.add(nombreLabel, gbc);
        gbc.gridx = 1; lamina.add(txt_nombre, gbc);

        gbc.gridy = 1; gbc.gridx = 0; lamina.add(sueldoLabel, gbc);
        gbc.gridx = 1; lamina.add(txt_sueldo, gbc);

        gbc.gridy = 2; gbc.gridx = 0; lamina.add(fecha, gbc);
        gbc.gridx = 1; lamina.add(txt_fecha, gbc);

        gbc.gridy = 3; gbc.gridx = 0; lamina.add(btn_Agregar, gbc);
        gbc.gridx = 1; lamina.add(btn_Guardar, gbc);
        gbc.gridx = 2; lamina.add(btn_Cargar, gbc);

        return lamina;
    }

    ArrayList<Empleado> empleados = new ArrayList<>();
    private void accion_Cargar() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("empleados.dat"));
            empleados = (ArrayList<Empleado>) ois.readObject();
            ois.close();
            modelo.setRowCount(0);
            for (Empleado empleado : empleados) {
                modelo.addRow(new Object[]{empleado.getNombre(), empleado.getSueldo(), empleado.getFecha_Contrato()});
            }
            JOptionPane.showMessageDialog(this, "Se cargo correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void accion_Guardar() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("empleados.dat"));
            oos.writeObject(empleados);
            oos.close();
            JOptionPane.showMessageDialog(this, "Se guardo correctamente", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void accion_Agregar() {
        try {
            String nombre = txt_nombre.getText();
            Double sueldo = Double.parseDouble(txt_sueldo.getText());
            String fecha_Contrato = txt_fecha.getText();
            Empleado empleado = new Empleado(nombre, sueldo, fecha_Contrato);
            empleados.add(empleado);
            modelo.addRow(new Object[]{empleado.getNombre(), empleado.getSueldo(), empleado.getFecha_Contrato()});
            txt_nombre.setText("");
            txt_sueldo.setText("");
            txt_fecha.setText("");
            JOptionPane.showMessageDialog(this, "Se agrego un empleado", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    DefaultTableModel modelo;
    JTable tabla;

    private JPanel lamina_tabla() {
        JPanel lamina = new JPanel();
        lamina.setLayout(new FlowLayout()); //Para poder modificar el tamanio de la tabla

        String[] columnas = {"Nombre", "Sueldo", "Fecha"};
        modelo = new DefaultTableModel(null, columnas);
        tabla = new JTable(modelo);

        tabla.setRowHeight(20); //Reducir la altura de las filas

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(480, 115));

        lamina.add(scroll);

        return lamina;
    }
}
