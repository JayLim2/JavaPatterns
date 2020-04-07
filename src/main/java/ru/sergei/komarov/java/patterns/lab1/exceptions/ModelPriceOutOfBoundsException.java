package ru.sergei.komarov.java.patterns.lab1.exceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {

    public ModelPriceOutOfBoundsException() {
    }

    /**
     * @param message casted to string invalid value of price
     */
    public ModelPriceOutOfBoundsException(String message) {
        super("Your value: " + message);
    }
}
