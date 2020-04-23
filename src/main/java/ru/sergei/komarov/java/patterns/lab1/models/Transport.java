package ru.sergei.komarov.java.patterns.lab1.models;

import ru.sergei.komarov.java.patterns.lab1.exceptions.DuplicateModelNameException;
import ru.sergei.komarov.java.patterns.lab1.exceptions.NoSuchModelNameException;

import java.io.Serializable;

public interface Transport extends Cloneable, Serializable {

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
