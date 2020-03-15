package ru.sergei.komarov.java.patterns.exceptions;

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
