package ru.sergei.komarov.java.patterns.lab1.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final String CONFIG_FILE_NAME = "config.properties";

    private static PropertiesReader instance = new PropertiesReader();

    private Properties property;

    private PropertiesReader() {
        property = new Properties();
        try {
            FileInputStream in = new FileInputStream(CommonConstants.RESOURCES_PATH + CONFIG_FILE_NAME);
            property.load(in);
            in.close();
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств не найден.");
        }
    }

    public static synchronized PropertiesReader getInstance() {
        return instance;
    }

    public Properties getProperty() {
        return property;
    }
}