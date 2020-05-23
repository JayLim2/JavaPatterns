package ru.sergei.komarov.java.patterns.lab3.template.method.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.ShapeTemplate;

public class MovementHandler implements EventHandler<ActionEvent> {

    private double DX = 7; //Step on x or velocity
    private double DY = 3; //Step on y

    private Pane canvas;
    private Shape object;
    private ShapeTemplate template;

    public MovementHandler(Pane canvas, Shape object, ShapeTemplate template) {
        this.canvas = canvas;
        this.object = object;
        this.template = template;
    }

    @Override
    public void handle(ActionEvent t) {
        double layoutX = object.getLayoutX() + DX;
        double layoutY = object.getLayoutY() + DY;

        //move the ball
        object.setLayoutX(layoutX);
        object.setLayoutY(layoutY);

        Bounds bounds = canvas.getBoundsInLocal();

        double width = template.getWidth(object);
        double height = template.getHeight(object);

        //If the ball reaches the left or right border make the step negative
        double minX = bounds.getMinX();
        double maxX = bounds.getMaxX();
        if (layoutX <= (minX + width) || layoutX >= (maxX - width)) {
            DX = -DX;
        }

        //If the ball reaches the bottom or top border make the step negative
        double minY = bounds.getMinY();
        double maxY = bounds.getMaxY();
        if (layoutY <= (minY + height) || layoutY >= (maxY - height)) {
            DY = -DY;
        }
    }
}
