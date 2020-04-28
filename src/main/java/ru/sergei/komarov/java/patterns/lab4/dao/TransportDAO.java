package ru.sergei.komarov.java.patterns.lab4.dao;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;

/**
 * Основной интерфейс DAO, позволяющий читать и записывать куда-либо
 * объект типа Transport.
 * <p>
 * Реализации:
 * 1. BinaryDAO - использует в качестве источника/хранилища данных байтовый файл
 * 2. TextDAO - использует в качестве источника/хранилища данных текстовый файл
 */
public interface TransportDAO {

    Transport read();

    void write(Transport transport);

}
