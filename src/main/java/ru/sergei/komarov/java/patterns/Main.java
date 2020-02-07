package ru.sergei.komarov.java.patterns;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties = PropertiesReader.getProperties();
        if(properties != null) {
            String testProperty = properties.getProperty("test.property");
            System.out.println("Test property: " + testProperty);
        }
    }
}
