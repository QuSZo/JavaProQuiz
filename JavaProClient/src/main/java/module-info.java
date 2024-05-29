module com.example.javapro {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.google.gson;
    requires java.sql;
    requires java.net.http;
    requires org.apache.commons.collections4;

    opens com.example.javapro to javafx.fxml;
    exports com.example.javapro;
    exports com.example.javapro.controller;
    opens com.example.javapro.controller to javafx.fxml;

    opens com.example.javapro.enums to com.google.gson;
    opens com.example.javapro.model.request.createQuiz to com.google.gson;
    opens com.example.javapro.model.request.userQuiz to com.google.gson;
    opens com.example.javapro.model.response.getDetailsQuiz to com.google.gson;
    opens com.example.javapro.model.response.getQuiz to com.google.gson;
}