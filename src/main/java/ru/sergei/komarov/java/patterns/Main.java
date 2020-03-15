package ru.sergei.komarov.java.patterns;

import ru.sergei.komarov.java.patterns.exceptions.NoSuchModelNameException;
import ru.sergei.komarov.java.patterns.models.Car;
import ru.sergei.komarov.java.patterns.utils.PropertiesReader;
import ru.sergei.komarov.java.patterns.utils.TransportUtils;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        //Test properties
        //testProperties();

        //Test transport
        Car car = new Car("Audi", 3);
        car.addModel("model1", 100);
        car.addModel("model2", 350);
        car.addModel("model3", 350);
        TransportUtils.printModelsNames(car);
        TransportUtils.printModelsPrices(car);
//        try {
//            car.removeModel("testNoSuchName");
//        } catch (NoSuchModelNameException e) {
//            e.printStackTrace();
//        }
        try {
            car.removeModel("model2");
        } catch (NoSuchModelNameException e) {
            e.printStackTrace();
        }
        TransportUtils.printModelsNames(car);
        TransportUtils.printModelsPrices(car);
        car.addModel("model4", 949);
        TransportUtils.printModelsNames(car);
        TransportUtils.printModelsPrices(car);
        try {
            car.removeModel("model1");
        } catch (NoSuchModelNameException e) {
            e.printStackTrace();
        }
        TransportUtils.printModelsNames(car);
        TransportUtils.printModelsPrices(car);
    }

    private static void testProperties() {
        Properties properties1 = PropertiesReader.getProperties();
        Properties properties2 = PropertiesReader.getProperties();

        if(properties1 != null) {
            String testProperty = properties1.getProperty("test.property");
            System.out.println("Test property: " + testProperty);

            boolean isSameObject = properties1 == properties2;
            System.out.println("Is same object: " + isSameObject);
        }
    }
}
