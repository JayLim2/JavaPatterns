package ru.sergei.komarov.java.patterns.lab1;

import ru.sergei.komarov.java.patterns.lab1.factories.AutoFactory;
import ru.sergei.komarov.java.patterns.lab1.factories.MotoFactory;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;
import ru.sergei.komarov.java.patterns.lab1.utils.PropertiesReader;
import ru.sergei.komarov.java.patterns.lab1.utils.TransportUtils;

import java.util.Properties;

public class Main {

    public static void main(String[] args) throws Exception {
        //Test properties
        testProperties();

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
        //clonedTransport.setPriceByName("moto2", 9999);
        clonedTransport.addModel("moto4", 567);

        System.out.println("== Original transport prices: ==");
        TransportUtils.printModelsPrices(transport);
        System.out.println("== Cloned transport prices: ==");
        TransportUtils.printModelsPrices(clonedTransport);

        System.out.println();

        //######### TEST CARS CLONING ##########
        System.out.println("######## CARS #############");
        TransportUtils.setFactory(autoFactory);
        transport = TransportUtils.createInstance("CarBrand A", 5);

        System.out.println(transport.getClass().getCanonicalName());

        clonedTransport = (Transport) transport.clone();
        //clonedTransport.setPriceByName("car3", 7777);
        clonedTransport.removeModel("car2");

        System.out.println("== Original transport prices: ==");
        TransportUtils.printModelsPrices(transport);
        System.out.println("== Cloned transport prices: ==");
        TransportUtils.printModelsPrices(clonedTransport);
    }

    private static void testProperties() {
        Properties properties1 = PropertiesReader.getInstance().getProperty();
        Properties properties2 = PropertiesReader.getInstance().getProperty();

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
