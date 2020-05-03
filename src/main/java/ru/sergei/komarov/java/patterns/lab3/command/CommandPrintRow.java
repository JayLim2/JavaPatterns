package ru.sergei.komarov.java.patterns.lab3.command;

import ru.sergei.komarov.java.patterns.lab1.models.Car;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CommandPrintRow implements Command {

    @Override
    public void printToFile(Car car, FileWriter writer) {
        try {
            writer.append("Марка: ");
            writer.append(car.getBrand());
            writer.append("; Модели: ");
            List<String> models = Arrays.asList(car.getModelsNames());
            models.forEach(modelName -> {
                try {
                    writer.append(modelName);
                    writer.append(models.indexOf(modelName) == models.size() - 1 ? "." : ", ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
