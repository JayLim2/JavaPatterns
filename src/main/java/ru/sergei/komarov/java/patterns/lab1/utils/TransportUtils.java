package ru.sergei.komarov.java.patterns.lab1.utils;

import ru.sergei.komarov.java.patterns.lab1.factories.AutoFactory;
import ru.sergei.komarov.java.patterns.lab1.factories.TransportFactory;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;
import ru.sergei.komarov.java.patterns.lab2.decorator.TransportDecorator;

public class TransportUtils {

    private static TransportFactory factory;

    static {
        factory = new AutoFactory();
    }

    /**
     * Set new factory
     *
     * @param factory factory object
     */
    public static void setFactory(TransportFactory factory) {
        TransportUtils.factory = factory;
    }

    /**
     * Creates new instance of transport with specified params by current factory
     *
     * @param brand       transport brand
     * @param modelsCount count of models (if MotorCycle - will be ignored)
     * @return transport object
     */
    public static Transport createInstance(String brand, int modelsCount) {
        return factory.createInstance(brand, modelsCount);
    }

    /**
     * Get average price of current transport models
     *
     * @param transport transport (car, motorcycle)
     * @return average price
     */
    public static double getAveragePrice(Transport transport) {
        double avg = 0;
        double[] prices = transport.getModelsPrices();
        for (double price : prices) {
            avg += price;
        }
        avg /= prices.length;
        return avg;
    }

    /**
     * Prints all models name by current transport
     *
     * @param transport transport object
     */
    public static void printModelsNames(Transport transport) {
        String[] names = transport.getModelsNames();
        System.out.println("All '" + transport.getBrand() + "' models names:");
        for (String name : names) {
            System.out.println("\t" + name);
        }
        System.out.println();
    }

    /**
     * Prints all models prices by current transport
     *
     * @param transport transport object
     */
    public static void printModelsPrices(Transport transport) {
        double[] prices = transport.getModelsPrices();
        System.out.println("All '" + transport.getBrand() + "' models prices:");
        for (double price : prices) {
            System.out.printf("\t%.2f %n", price);
        }
        System.out.println();
    }

    /**
     * Returns synchronized Transport decorator
     *
     * @param transport original object
     * @return synchronized decorator
     */
    public static Transport synchronizedTransport(Transport transport) {
        return new TransportDecorator(transport);
    }

}
