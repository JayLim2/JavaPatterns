package ru.sergei.komarov.java.patterns.lab3.сhain.of.responsibility;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;

public abstract class ChainOfResponsibility {

    private ChainOfResponsibility next;

    /**
     * Для построения цепи из объектов-проверок.
     */
    public ChainOfResponsibility linkWith(ChainOfResponsibility next) {
        this.next = next;
        return next;
    }

    public abstract void writeTransportInfoToFile(Transport transport);

    /**
     * Запускает проверку в следующем объекте или завершает проверку, если мы в
     * последнем элементе цепи.
     */
    boolean checkNext(Transport transport) {
        if (next == null) {
            return false;
        }
        if (transport.getModelsCount() > 3) {
            next.writeTransportInfoToFile(transport);
            return false;
        }
        return true;
    }
}
