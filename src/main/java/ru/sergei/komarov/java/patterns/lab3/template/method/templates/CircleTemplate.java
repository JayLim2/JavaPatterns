package ru.sergei.komarov.java.patterns.lab3.template.method.templates;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class CircleTemplate implements ShapeTemplate {

    private static final CircleTemplate instance = new CircleTemplate();

    private double speedInMillis;

    private CircleTemplate() {
        speedInMillis = 20;
    }

    public synchronized static ShapeTemplate getInstance() {
        return instance;
    }

    @Override
    public double getWidth(Shape shape) {
        if (shape instanceof Circle) {
            return ((Circle) shape).getRadius();
        }
        throw new IllegalArgumentException("This class receives only Circles.");
    }

    @Override
    public double getHeight(Shape shape) {
        return getWidth(shape);
    }

    @Override
    public double getSpeedInMillis() {
        return speedInMillis;
    }

    @Override
    public void setSpeedInMillis(double ms) {
        if (Double.compare(ms, 0) <= 0) {
            throw new IllegalArgumentException("Allowed only positive values of speed.");
        }
    }

    @Override
    public StartingPointType getStartingPointType() {
        return StartingPointType.CENTER;
    }
}
