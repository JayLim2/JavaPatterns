package ru.sergei.komarov.java.patterns.lab3.template.method.templates;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class RectangleTemplate implements ShapeTemplate {
    private static final RectangleTemplate instance = new RectangleTemplate();

    private double speedInMillis;

    private RectangleTemplate() {
        speedInMillis = 30;
    }

    public synchronized static ShapeTemplate getInstance() {
        return instance;
    }

    @Override
    public double getWidth(Shape shape) {
        if (shape instanceof Rectangle) {
            return ((Rectangle) shape).getWidth();
        }
        throw new IllegalArgumentException("This class receives only Rectangles.");
    }

    @Override
    public double getHeight(Shape shape) {
        if (shape instanceof Rectangle) {
            return ((Rectangle) shape).getHeight();
        }
        throw new IllegalArgumentException("This class receives only Rectangles.");
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
}
