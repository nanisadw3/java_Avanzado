package org.example.javapostgres;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javax.xml.transform.Result;
import java.sql.*;

public class Controller {
    @FXML
    public Button btn_Conectar;
    @FXML
    public TextArea txtArea;

    @FXML
    public void accionConeccion() {
        Thread t = new Thread(()->{
            String resultado = "";
            try {
                Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Postgres", "postgres", "246810");
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM \"Usuarios\"");
                while (rs.next()) {
                    resultado +=("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + "\n");
                }
                txtArea.setText(resultado);
                c.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}