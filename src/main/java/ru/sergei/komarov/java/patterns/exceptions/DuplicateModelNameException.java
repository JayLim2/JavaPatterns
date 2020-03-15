package ru.sergei.komarov.java.patterns.exceptions;

public class DuplicateModelNameException extends Exception {

    private static final String COMMON_PART = "Model with such name already exists.";

    public DuplicateModelNameException() {
        this(COMMON_PART);
    }

    public DuplicateModelNameException(String message) {
        super(COMMON_PART + " Name: " + message);
    }
}
