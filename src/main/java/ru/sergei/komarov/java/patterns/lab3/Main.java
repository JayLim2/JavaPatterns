package ru.sergei.komarov.java.patterns.lab3;

import ru.sergei.komarov.java.patterns.lab1.exceptions.DuplicateModelNameException;
import ru.sergei.komarov.java.patterns.lab1.exceptions.NoSuchModelNameException;
import ru.sergei.komarov.java.patterns.lab1.models.Car;
import ru.sergei.komarov.java.patterns.lab1.models.Motorcycle;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;
import ru.sergei.komarov.java.patterns.lab1.utils.TransportUtils;
import ru.sergei.komarov.java.patterns.lab3.command.CommandPrintColumn;
import ru.sergei.komarov.java.patterns.lab3.visitor.PrintVisitor;
import ru.sergei.komarov.java.patterns.lab3.сhain.of.responsibility.ChainOfResponsibility;
import ru.sergei.komarov.java.patterns.lab3.сhain.of.responsibility.ChainPrintColumn;
import ru.sergei.komarov.java.patterns.lab3.сhain.of.responsibility.ChainPrintRow;

import java.io.FileWriter;
import java.io.IOException;

public class Main {

    private static final String CAR_BRAND = "CarBrand";

    public static void main(String[] args) throws Exception {
        /* Chain Of Responsibility Test */
        System.out.println("=== Chain of responsibility ===");
        Transport car = TransportUtils.createInstance(CAR_BRAND, 4);
        ChainOfResponsibility chain = new ChainPrintRow();
        chain.attachNext(new ChainPrintColumn());
        chain.writeTransportToFile(car);
        System.out.println();

        /* Command Test */
        System.out.println("=== Command ===");
        Car car2 = new Car(CAR_BRAND, 3);
        try {
            boolean isAppendEnabled = false;
            car2.print(new FileWriter("TransportDataCommandRow.txt", isAppendEnabled));
            car2.setPrintCommand(new CommandPrintColumn());
            car2.print(new FileWriter("TransportDataCommandColumn.txt", isAppendEnabled));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println();

        /* Memento Test */
        System.out.println("=== Memento ===");
        Car car3 = new Car(CAR_BRAND, 3);
        car3.createMemento();
        TransportUtils.printModelsNames(car3);
        try {
            car3.setModelName("car1", "CAR_1");
        } catch (NoSuchModelNameException | DuplicateModelNameException e) {
            e.printStackTrace();
        }
        TransportUtils.printModelsNames(car3);
        car3 = car3.setMemento();
        TransportUtils.printModelsNames(car3);
        System.out.println();

        /* Visitor Test */
        System.out.println("=== Visitor ===");
        Car car4 = new Car("ВАЗ", 3);
        car4.accept(new PrintVisitor());
        System.out.println("\n");
        Motorcycle motorcycle = new Motorcycle("Kawasaki");
        motorcycle.addModel("moto1", 100);
        motorcycle.addModel("moto2", 200);
        motorcycle.addModel("moto3", 300);
        motorcycle.accept(new PrintVisitor());
    }
}
