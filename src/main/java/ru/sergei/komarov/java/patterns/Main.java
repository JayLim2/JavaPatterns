package ru.sergei.komarov.java.patterns;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        Properties properties1 = PropertiesReader.getProperties();
        Properties properties2 = PropertiesReader.getProperties();

        if(properties1 != null) {
            String testProperty = properties1.getProperty("test.property");
            System.out.println("Test property: " + testProperty);

            boolean isSameObject = properties1 == properties2;
            System.out.println("Is same object: " + isSameObject);
        }
    }
}
