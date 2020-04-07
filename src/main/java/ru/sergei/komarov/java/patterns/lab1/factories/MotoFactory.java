package ru.sergei.komarov.java.patterns.lab1.factories;

import ru.sergei.komarov.java.patterns.lab1.models.Motorcycle;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;

public class MotoFactory implements TransportFactory {

    @Override
    public Transport createInstance(String brand, int modelsCount) {
        return new Motorcycle(brand);
    }

}
