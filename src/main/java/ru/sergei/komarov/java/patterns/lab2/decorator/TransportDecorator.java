package ru.sergei.komarov.java.patterns.lab2.decorator;

import ru.sergei.komarov.java.patterns.lab1.exceptions.DuplicateModelNameException;
import ru.sergei.komarov.java.patterns.lab1.exceptions.NoSuchModelNameException;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;

public class TransportDecorator implements Transport {
    private Transport transport;

    public TransportDecorator(Transport vehicle) {
        this.transport = vehicle;
    }

    @Override
    public synchronized String getBrand() {
        return transport.getBrand();
    }

    @Override
    public synchronized void setBrand(String brand) {
        transport.setBrand(brand);
    }

    @Override
    public synchronized String[] getModelsNames() {
        return transport.getModelsNames();
    }

    @Override
    public synchronized double getPriceByName(String titleModel) throws NoSuchModelNameException {
        return transport.getPriceByName(titleModel);
    }

    @Override
    public synchronized void setPriceByName(String titleModel, double newPrice) throws NoSuchModelNameException {
        transport.setPriceByName(titleModel, newPrice);
    }

    @Override
    public synchronized double[] getModelsPrices() {
        return transport.getModelsPrices();
    }

    @Override
    public synchronized void removeModel(String titleModel) throws NoSuchModelNameException {
        transport.removeModel(titleModel);
    }

    @Override
    public synchronized void addModel(String titleModel, double priceModel) throws DuplicateModelNameException {
        transport.addModel(titleModel, priceModel);
    }

    @Override
    public synchronized void setModelName(String oldTitleModel, String newTitleModel) throws NoSuchModelNameException, DuplicateModelNameException {
        transport.setModelName(oldTitleModel, newTitleModel);
    }

    @Override
    public synchronized int getModelsCount() {
        return transport.getModelsCount();
    }

    @Override
    public synchronized Object clone() throws CloneNotSupportedException {
        return transport.clone();
    }
}
