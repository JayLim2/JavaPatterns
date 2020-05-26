package ru.sergei.komarov.java.patterns.lab3.template.method.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import ru.sergei.komarov.java.patterns.lab3.template.method.handlers.MovementHandler;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.CircleTemplate;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.RectangleTemplate;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.ShapeTemplate;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.StarTemplate;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JumpersController {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(3);

    @FXML
    private AnchorPane canvas;

    public void initialize() {

    }

    public void onStartClick() {
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();

        double radius, width, height;

        Random random = new Random();
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                radius = 10.0;
                Circle circle = new Circle(radius, Color.CADETBLUE);
                ShapeTemplate circleTemplate = CircleTemplate.getInstance();
                circle.relocate(canvasWidth - 2 * radius, canvasHeight - 2 * radius);
                canvas.getChildren().add(circle);
                //run animation
                startTimeline(circle, circleTemplate);
                break;
            case 1:
                width = 25;
                height = 12;
                Rectangle rectangle = new Rectangle(width, height, Color.RED);
                ShapeTemplate rectangleTemplate = RectangleTemplate.getInstance();
                rectangle.relocate(canvasWidth - width, canvasHeight - height);
                canvas.getChildren().add(rectangle);
                //run animation
                startTimeline(rectangle, rectangleTemplate);
                break;
            case 2:
                Polygon star = getStar(0.4, 0, 0);
                star.setFill(Color.INDIGO);
                ShapeTemplate starTemplate = StarTemplate.getInstance();
                width = starTemplate.getWidth(star);
                height = starTemplate.getHeight(star);
                star.relocate(canvasWidth - width, canvasHeight - height);
                canvas.getChildren().add(star);
                //run animation
                startTimeline(star, starTemplate);
        }
    }

    public void onCloseClick() {
        System.exit(0);
    }

    private void startTimeline(Shape shape, ShapeTemplate template) {
        Platform.runLater(
                () -> {
                    Timeline timeline = new Timeline(
                            new KeyFrame(
                                    Duration.millis(template.getSpeedInMillis()),
                                    new MovementHandler(canvas, shape, template)
                            )
                    );
                    timeline.setCycleCount(Timeline.INDEFINITE);
                    timeline.play();
                }
        );
    }

    private Polygon getStar(double scale, double translateX, double translateY) {

        double[] points = {
                10, 85,
                85, 75,
                110, 10,
                135, 75,
                210, 85,
                160, 125,
                170, 190,
                110, 150,
                50, 190,
                60, 125
        };

        for (int i = 0; i < points.length; i++) {
            points[i] = scale * points[i] + (i % 2 == 0 ? translateX : translateY);
        }

        return new Polygon(points);
    }

}