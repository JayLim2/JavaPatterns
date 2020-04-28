package ru.sergei.komarov.java.patterns.lab4.mvc.models;

import java.util.Objects;

/**
 * Класс модели данных
 * <p>
 * Определяет объект точки (содержит координаты, координата Y вычисляется автоматически)
 */
public class Point {
    private Double x;
    private Double y;

    public Point(Double x) {
        this.x = x;
        this.y = Math.pow(x + 1, 2);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
        this.y = Math.pow(x + 1, 2);
    }

    public Double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point that = (Point) o;
        return Objects.equals(x, that.x) &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}