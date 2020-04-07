package ru.sergei.komarov.java.patterns.lab1;

import ru.sergei.komarov.java.patterns.lab1.exceptions.NoSuchModelNameException;
import ru.sergei.komarov.java.patterns.lab1.factories.AutoFactory;
import ru.sergei.komarov.java.patterns.lab1.factories.MotoFactory;
import ru.sergei.komarov.java.patterns.lab1.models.Car;
import ru.sergei.komarov.java.patterns.lab1.models.Motorcycle;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;
import ru.sergei.komarov.java.patterns.lab1.utils.PropertiesReader;
import ru.sergei.komarov.java.patterns.lab1.utils.TransportUtils;

import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        //Test properties
        testProperties();

        //Test cars and motors
        //testCars();
        //testMoto();

        //Test transport and factories
        AutoFactory autoFactory = new AutoFactory();
        MotoFactory motoFactory = new MotoFactory();

        Transport transport, clonedTransport;

        //######### TEST MOTO CLONING ##########
        System.out.println("######## MOTO #############");
        TransportUtils.setFactory(motoFactory);
        transport = TransportUtils.createInstance("MotoBrand A", 3);
        transport.addModel("moto1", 100);
        transport.addModel("moto2", 200);
        transport.addModel("moto3", 300);

        System.out.println(transport.getClass().getCanonicalName());

        clonedTransport = (Transport) transport.clone();
        clonedTransport.setPriceByName("moto2", 9999);

        System.out.println("== Original transport prices: ==");
        TransportUtils.printModelsPrices(transport);
        System.out.println("== Cloned transport prices: ==");
        TransportUtils.printModelsPrices(clonedTransport);

        System.out.println();

        //######### TEST CARS CLONING ##########
        System.out.println("######## CARS #############");
        TransportUtils.setFactory(autoFactory);
        transport = TransportUtils.createInstance("CarBrand A", 5);
        transport.addModel("car1", 100);
        transport.addModel("car2", 200);
        transport.addModel("car3", 300);

        System.out.println(transport.getClass().getCanonicalName());

        clonedTransport = (Transport) transport.clone();
        clonedTransport.setPriceByName("car3", 7777);

        System.out.println("== Original transport prices: ==");
        TransportUtils.printModelsPrices(transport);
        System.out.println("== Cloned transport prices: ==");
        TransportUtils.printModelsPrices(clonedTransport);
    }

    private static void testCars() throws Exception {
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

    private static void testMoto() throws Exception {
        Motorcycle moto = new Motorcycle("YAMAHA");
        moto.addModel("model1", 100);
        moto.addModel("model2", 350);
        moto.addModel("model3", 350);
        TransportUtils.printModelsNames(moto);
        TransportUtils.printModelsPrices(moto);
//        try {
//            car.removeModel("testNoSuchName");
//        } catch (NoSuchModelNameException e) {
//            e.printStackTrace();
//        }
        try {
            moto.removeModel("model2");
        } catch (NoSuchModelNameException e) {
            e.printStackTrace();
        }
        TransportUtils.printModelsNames(moto);
        TransportUtils.printModelsPrices(moto);
        moto.addModel("model4", 949);
        TransportUtils.printModelsNames(moto);
        TransportUtils.printModelsPrices(moto);
        try {
            moto.removeModel("model1");
        } catch (NoSuchModelNameException e) {
            e.printStackTrace();
        }
        TransportUtils.printModelsNames(moto);
        TransportUtils.printModelsPrices(moto);
    }

    private static void testProperties() {
        Properties properties1 = PropertiesReader.getProperties();
        Properties properties2 = PropertiesReader.getProperties();

        if (properties1 != null) {
            String testProperty = properties1.getProperty("test.property");
            System.out.println("Test property: " + testProperty);

            String secondProperty = properties1.getProperty("second.property");
            System.out.println("Second property: " + secondProperty);

            boolean isSameObject = properties1 == properties2;
            System.out.println("Is same object: " + isSameObject);

            System.out.println();
        }
    }
}
