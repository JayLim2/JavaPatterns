package ru.sergei.komarov.java.patterns.lab3.template.method;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TemplateMethodMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = "/jumpers.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(path));
        primaryStage.setTitle("Лабораторная работа №3 - Паттерн Template Method");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
