package ru.sergei.komarov.java.patterns.factories;

import ru.sergei.komarov.java.patterns.models.Transport;

public interface TransportFactory {

    Transport createInstance(String brand, int modelsCount);

}
