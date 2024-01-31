module com.example.appgestiondesconges {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;


    opens com.example.appgestiondesconges to javafx.fxml;
    exports com.example.appgestiondesconges;
}