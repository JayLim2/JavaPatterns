package ru.sergei.komarov.java.patterns.lab3.template.method.templates;

import javafx.collections.ObservableList;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public class StarTemplate implements ShapeTemplate {

    private static final StarTemplate instance = new StarTemplate();

    private double speedInMillis;

    private StarTemplate() {
        speedInMillis = 50;
    }

    public static ShapeTemplate getInstance() {
        return instance;
    }

    @Override
    public double getWidth(Shape shape) {
        Polygon star = (Polygon) shape;
        ObservableList<Double> points = star.getPoints();
        double minX = points.get(0);
        double maxX = points.get(0);
        for (int i = 0; i < points.size(); i++) {
            double value = points.get(i);
            if (i % 2 == 0) {
                if (value < minX) minX = value;
                if (value > maxX) maxX = value;
            }
        }
        return maxX - minX;
    }

    @Override
    public double getHeight(Shape shape) {
        Polygon star = (Polygon) shape;
        ObservableList<Double> points = star.getPoints();
        double minY = points.get(1);
        double maxY = points.get(1);
        for (int i = 1; i < points.size(); i++) {
            double value = points.get(i);
            if (i % 2 != 0) {
                if (value < minY) minY = value;
                if (value > maxY) maxY = value;
            }
        }
        return maxY - minY;
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
        return StartingPointType.TOP_LEFT;
    }
}
