package ru.sergei.komarov.java.patterns.lab1.factories;

import ru.sergei.komarov.java.patterns.lab1.models.Car;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;

public class AutoFactory implements TransportFactory {

    @Override
    public Transport createInstance(String brand, int modelsCount) {
        return new Car(brand, modelsCount);
    }
}
