package ru.sergei.komarov.java.patterns;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final String CONFIG_FILE_NAME = "config.properties";

    public static Properties getProperties() {
        Properties properties = new Properties();

        try(FileInputStream in = new FileInputStream(getPropertiesFilePath())) {
            properties.load(in);
            return properties;
        } catch (IOException e) {
            System.out.println("Input/Output exception.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Another exception.");
            e.printStackTrace();
        }

        return null;
    }

    private static String getPropertiesFilePath() {
        return "src/main/resources/" + CONFIG_FILE_NAME;
    }

}
