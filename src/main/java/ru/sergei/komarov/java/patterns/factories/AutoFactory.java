package ru.sergei.komarov.java.patterns.factories;

import ru.sergei.komarov.java.patterns.models.Car;
import ru.sergei.komarov.java.patterns.models.Transport;

public class AutoFactory implements TransportFactory {

    @Override
    public Transport createInstance(String brand, int modelsCount) {
        return new Car(brand, modelsCount);
    }
}
