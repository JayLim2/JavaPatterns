package ru.sergei.komarov.java.patterns.lab3.observer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ObserverMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = "/smile.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(path));
        primaryStage.setTitle("Лабораторная работа №3 - Паттерн Observer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
