package ru.sergei.komarov.java.patterns.lab1.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final String CONFIG_FILE_NAME = "config.properties";

    private static Properties properties;

    public static Properties getProperties() {
        if(properties == null) {
            try(FileInputStream in = new FileInputStream(getPropertiesFilePath())) {
                properties = new Properties();
                properties.load(in);
            } catch (IOException e) {
                System.out.println("Input/Output exception.");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Another exception.");
                e.printStackTrace();
            }
        }
        return properties;
    }

    private static String getPropertiesFilePath() {
        return CommonConstants.RESOURCES_PATH + CONFIG_FILE_NAME;
    }

}
