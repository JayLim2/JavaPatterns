package ru.sergei.komarov.java.patterns.lab3.template.method.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.ShapeTemplate;

public class MovementHandler implements EventHandler<ActionEvent> {

    private double DX = -7; //Step on x or velocity
    private double DY = -3; //Step on y

    //freezed bounds
    private double MIN_X;
    private double MAX_X;
    private double MIN_Y;
    private double MAX_Y;

    //Main components
    private Shape object;
    private ShapeTemplate template;

    public MovementHandler(Pane canvas, Shape object, ShapeTemplate template) {
        this.object = object;
        this.template = template;

        Bounds bounds = canvas.getBoundsInLocal();
        MIN_X = bounds.getMinX();
        MAX_X = bounds.getMaxX();
        MIN_Y = bounds.getMinY();
        MAX_Y = bounds.getMaxY();
    }

    @Override
    public void handle(ActionEvent t) {
        double layoutX = object.getLayoutX() + DX;
        double layoutY = object.getLayoutY() + DY;

        //move the ball
        object.setLayoutX(layoutX);
        object.setLayoutY(layoutY);

        double width = template.getWidth(object);
        double height = template.getHeight(object);

        //Если объект достигает левого или правого края - инвертим знак у DX
        if (layoutX <= (MIN_X + width) || layoutX >= (MAX_X - width)) {
            DX = -DX;
        }

        //Если объект достигает нижнего или верхнего края - инвертим знак у DY
        if (layoutY <= (MIN_Y + height) || layoutY >= (MAX_Y - height)) {
            DY = -DY;
        }
    }
}
