package ru.sergei.komarov.java.patterns.lab3.сhain.of.responsibility;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ChainPrintRow extends ChainOfResponsibility {

    @Override
    public void writeTransportInfoToFile(Transport transport) {
        if (checkNext(transport)) {
            try (FileWriter writer = new FileWriter("TransportData.txt", false)) {
                writer.append("Марка: ");
                writer.append(transport.getBrand());
                writer.append("; Модели: ");
                List<String> models = Arrays.asList(transport.getModelsNames());
                models.forEach(modelName -> {
                    try {
                        writer.append(modelName);
                        writer.append(models.indexOf(modelName) == models.size() - 1 ? "." : ", ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
