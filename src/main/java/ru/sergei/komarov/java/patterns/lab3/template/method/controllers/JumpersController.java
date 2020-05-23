package ru.sergei.komarov.java.patterns.lab3.template.method.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import ru.sergei.komarov.java.patterns.lab3.template.method.handlers.MovementHandler;

public class JumpersController {

    @FXML
    private AnchorPane canvas;

    public void initialize() {
        Circle ball = new Circle(10, Color.CADETBLUE);
        ball.relocate(5, 5);

        canvas.getChildren().add(ball);

        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(20),
                        new MovementHandler(canvas, ball)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void onStartClick() {

    }

    public void onCloseClick() {
        System.exit(0);
    }

}