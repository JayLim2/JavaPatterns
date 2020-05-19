package ru.sergei.komarov.java.patterns.lab3.сhain.of.responsibility;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;

public abstract class ChainOfResponsibility {

    private ChainOfResponsibility next;

    public abstract void writeTransportToFile(Transport vehicle);

    public void attachNext(ChainOfResponsibility chainItem) {
        next = chainItem;
    }

    public ChainOfResponsibility getNext() {
        return next;
    }
}
