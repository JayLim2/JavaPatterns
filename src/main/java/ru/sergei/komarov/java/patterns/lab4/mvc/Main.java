package ru.sergei.komarov.java.patterns.lab4.mvc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * MVC - Model-View-Controller
 * <p>
 * Model (Модель) - это некоторые данные.
 * View (Представление) - это визуальное представление этих данных.
 * Controller (Контроллер) - это компонент, который управляет Представлением на основе
 * изменяющихся данных (например, перерисовывает график, если изменились координаты точек).
 * <p>
 * В данной работе:
 * Model: класс Point.java, содержит координаты точки, координата Y рассчитывается автоматически
 * при изменении координаты x.
 * View: main.fxml - содержит базовый код конфигурации формы.
 * Controller: класс Controller.java, содержит методы, которые изменяют данные,
 * тем самым триггерят перерисовку View.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String path = "/main.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(path));
        primaryStage.setTitle("Лабораторная работа №4");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
