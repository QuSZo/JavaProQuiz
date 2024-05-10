module com.example.javapro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javapro to javafx.fxml;
    exports com.example.javapro;
    exports com.example.javapro.controller;
    opens com.example.javapro.controller to javafx.fxml;
}