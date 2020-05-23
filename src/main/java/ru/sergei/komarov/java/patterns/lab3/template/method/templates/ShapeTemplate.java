package ru.sergei.komarov.java.patterns.lab3.template.method.templates;

import javafx.scene.shape.Shape;

public interface ShapeTemplate {

    double getWidth(Shape shape);

    double getHeight(Shape shape);

    double getSpeedInMillis();

    void setSpeedInMillis(double ms);

    StartingPointType getStartingPointType();

}
