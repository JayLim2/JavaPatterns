package ru.sergei.komarov.java.patterns.lab1.exceptions;

public class NoSuchModelNameException extends Exception {

    private static final String COMMON_PART = "Model with such name doesn't exists.";

    public NoSuchModelNameException() {
        this(COMMON_PART);
    }

    public NoSuchModelNameException(String message) {
        super(COMMON_PART + " Name: " + message);
    }
}
