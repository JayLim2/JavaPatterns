package ru.sergei.komarov.java.patterns.lab1.factories;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;

public interface TransportFactory {

    Transport createInstance(String brand, int modelsCount);

}
