package ru.sergei.komarov.java.patterns.lab3.template.method.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class MovementHandler implements EventHandler<ActionEvent> {

    private static double DX = 7; //Step on x or velocity
    private static double DY = 3; //Step on y

    private Pane canvas;
    private Circle object; //TODO need type Node

    public MovementHandler(Pane canvas, Circle object) {
        this.canvas = canvas;
        this.object = object;
    }

    @Override
    public void handle(ActionEvent t) {
        double width = object.getRadius();
        double layoutX = object.getLayoutX() + DX;
        double layoutY = object.getLayoutY() + DY;

        //move the ball
        object.setLayoutX(layoutX);
        object.setLayoutY(layoutY);

        Bounds bounds = canvas.getBoundsInLocal();

        //If the ball reaches the left or right border make the step negative
        double minX = bounds.getMinX();
        double maxX = bounds.getMaxX();
        if (layoutX <= (minX + width) || layoutX >= (maxX - width)) {
            DX = -DX;
        }

        //If the ball reaches the bottom or top border make the step negative
        double minY = bounds.getMinY();
        double maxY = bounds.getMaxY();
        if (layoutY <= (minY + width) || layoutY >= (maxY - width)) {
            DY = -DY;
        }
    }
}
