package ru.sergei.komarov.java.patterns.lab3.command;

import ru.sergei.komarov.java.patterns.lab1.models.Car;

import java.io.FileWriter;

public interface Command {

    void printToFile(Car car, FileWriter writer);
}
