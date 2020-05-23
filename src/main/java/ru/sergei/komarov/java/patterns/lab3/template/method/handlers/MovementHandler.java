package ru.sergei.komarov.java.patterns.lab3.template.method.handlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.ShapeTemplate;
import ru.sergei.komarov.java.patterns.lab3.template.method.templates.StartingPointType;

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
    private StartingPointType startingPointType;

    public MovementHandler(Pane canvas, Shape object, ShapeTemplate template) {
        this.object = object;
        this.template = template;
        this.startingPointType = template.getStartingPointType();

        Bounds bounds = canvas.getBoundsInLocal();
        MIN_X = bounds.getMinX();
        MAX_X = bounds.getMaxX();
        MIN_Y = bounds.getMinY();
        MAX_Y = bounds.getMaxY();
    }

    @Override
    public void handle(ActionEvent t) {
        //System.out.println(object.getLayoutX());
        //if(true) return;

        double layoutX = object.getLayoutX() + DX;
        double layoutY = object.getLayoutY() + DY;

        //move the ball
        object.setLayoutX(layoutX);
        object.setLayoutY(layoutY);

        double width = template.getWidth(object);
        double height = template.getHeight(object);

        //Если объект достигает левого или правого края - инвертим знак у DX
        double left = MIN_X + (startingPointType == StartingPointType.TOP_LEFT ? 0 : width);
        double right = MAX_X - width;
        if (layoutX <= left || layoutX >= right) {
            DX = -DX;
        }

        //Если объект достигает нижнего или верхнего края - инвертим знак у DY
        double top = MIN_Y + (startingPointType == StartingPointType.TOP_LEFT ? 0 : height);
        double bottom = MAX_Y - height;
        if (layoutY <= top || layoutY >= bottom) {
            DY = -DY;
        }
    }
}
