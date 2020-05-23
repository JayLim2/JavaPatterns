package ru.sergei.komarov.java.patterns.lab3.template.method.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import ru.sergei.komarov.java.patterns.lab3.template.method.handlers.MovementHandler;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.CircleTemplate;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.RectangleTemplate;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.ShapeTemplate;

import java.util.Random;

public class JumpersController {

    @FXML
    private AnchorPane canvas;

    public void initialize() {

    }

    public void onStartClick() {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        Random random = new Random();
        int type = random.nextInt(2);
//        type = 1;
        switch (type) {
            case 0:
                double radius = 10.0;
                Circle circle = new Circle(radius, Color.CADETBLUE);
                ShapeTemplate circleTemplate = CircleTemplate.getInstance();
                circle.relocate(canvasWidth - radius / 2, canvasHeight - radius / 2);
                canvas.getChildren().add(circle);
                startTimeline(circle, circleTemplate);
                break;
            case 1:
                double width = 25;
                double height = 12;
                Rectangle rectangle = new Rectangle(width, height, Color.RED);
                ShapeTemplate rectangleTemplate = RectangleTemplate.getInstance();
                rectangle.relocate(canvasWidth - width, canvasHeight - height);
                canvas.getChildren().add(rectangle);
                startTimeline(rectangle, rectangleTemplate);
        }
    }

    public void onCloseClick() {
        System.exit(0);
    }

    private void startTimeline(Shape shape, ShapeTemplate template) {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(template.getSpeedInMillis()),
                        new MovementHandler(canvas, shape, template)
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}