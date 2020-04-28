package ru.sergei.komarov.java.patterns.lab4.dao;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;

public interface TransportDAO {

    Transport read();

    void write(Transport transport);

}
