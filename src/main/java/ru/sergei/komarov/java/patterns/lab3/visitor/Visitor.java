package ru.sergei.komarov.java.patterns.lab3.visitor;

import ru.sergei.komarov.java.patterns.lab1.models.Car;
import ru.sergei.komarov.java.patterns.lab1.models.Motorcycle;

public interface Visitor {

    void visit(Car car);

    void visit(Motorcycle motorcycle);

}
