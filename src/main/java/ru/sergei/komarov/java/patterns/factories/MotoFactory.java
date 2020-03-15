package ru.sergei.komarov.java.patterns.factories;

import ru.sergei.komarov.java.patterns.models.Motorcycle;
import ru.sergei.komarov.java.patterns.models.Transport;

public class MotoFactory implements TransportFactory {

    @Override
    public Transport createInstance(String brand, int modelsCount) {
        return new Motorcycle(brand);
    }

}
