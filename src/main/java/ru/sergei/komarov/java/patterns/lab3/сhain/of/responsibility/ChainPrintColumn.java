package ru.sergei.komarov.java.patterns.lab3.сhain.of.responsibility;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;

import java.io.File;
import java.io.PrintWriter;

import static ru.sergei.komarov.java.patterns.lab1.utils.CommonConstants.CHAIN_RESULT_FILE;

public class ChainPrintColumn extends ChainOfResponsibility {

    @Override
    public void writeTransportToFile(Transport transport) {
        ChainOfResponsibility next = getNext();
        if (next == null || transport.getModelsCount() > 3) {
            try (PrintWriter out = new PrintWriter(new File(CHAIN_RESULT_FILE))) {
                out.println("Марка: " + transport.getBrand());
                out.println("Количество: " + transport.getModelsCount());
                String[] models = transport.getModelsNames();
                for (String model : models) {
                    out.write(model);
                    out.write("\n");
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            next.writeTransportToFile(transport);
        }
    }

}
