module org.example.javapostgres {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.javapostgres to javafx.fxml;
    exports org.example.javapostgres;
}