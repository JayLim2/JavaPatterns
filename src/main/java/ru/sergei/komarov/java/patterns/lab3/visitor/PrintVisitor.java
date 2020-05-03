package ru.sergei.komarov.java.patterns.lab3.visitor;

import ru.sergei.komarov.java.patterns.lab1.models.Car;
import ru.sergei.komarov.java.patterns.lab1.models.Motorcycle;

import java.util.Arrays;
import java.util.List;

public class PrintVisitor implements Visitor {

    public void visit(Car car) {
        System.out.print("Марка: ");
        System.out.print((car.getBrand()));
        System.out.print(("; Модели: "));
        List<String> models = Arrays.asList(car.getModelsNames());
        models.forEach(modelName -> {
            System.out.print(modelName);
            System.out.print(models.indexOf(modelName) == models.size() - 1 ? "." : ", ");
        });
    }

    public void visit(Motorcycle motorcycle) {
        System.out.print("Марка: ");
        System.out.println((motorcycle.getBrand()) + ";");
        System.out.println(("Модели: "));
        List<String> models = Arrays.asList(motorcycle.getModelsNames());
        models.forEach(modelName -> {
            System.out.print(modelName);
            System.out.println(models.indexOf(modelName) == models.size() - 1 ? "." : ", ");
        });
    }
}
