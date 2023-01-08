module com.example.smo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.smo to javafx.fxml;
    exports com.example.smo;
    exports animations;
    opens animations to javafx.fxml;
}