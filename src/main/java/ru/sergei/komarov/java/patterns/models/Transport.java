package ru.sergei.komarov.java.patterns.models;

import ru.sergei.komarov.java.patterns.exceptions.DuplicateModelNameException;
import ru.sergei.komarov.java.patterns.exceptions.NoSuchModelNameException;

public interface Transport extends Cloneable {

    String getBrand();

    void setBrand(String brand);

    void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;

    String[] getModelsNames();

    double[] getModelsPrices();

    double getPriceByName(String name) throws NoSuchModelNameException;

    void setPriceByName(String name, double newPrice) throws NoSuchModelNameException;

    void addModel(String name, double price) throws DuplicateModelNameException;

    void removeModel(String name) throws NoSuchModelNameException;

    int getModelsCount();

    Object clone() throws CloneNotSupportedException;

}
