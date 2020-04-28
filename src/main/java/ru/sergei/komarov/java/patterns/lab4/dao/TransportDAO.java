package ru.sergei.komarov.java.patterns.lab4.dao;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;
import ru.sergei.komarov.java.patterns.lab1.utils.TransportUtils;

import java.io.*;
import java.util.Scanner;

public class TransportDAO {

    public Transport read(FileType fileType) {
        String filename = fileType.getFilename();

        Transport transport = null;
        String brand = null;
        int modelsCount = 0;
        int k = 0;

        switch (fileType) {
            case TEXT:
                try (Scanner in = new Scanner(new FileReader(filename))) {
                    if (in.hasNextLine()) {
                        brand = in.nextLine().trim();
                    }
                    if (in.hasNextLine()) {
                        modelsCount = Integer.parseInt(in.nextLine().trim());
                    }
                    transport = TransportUtils.createInstance(brand, 0);
                    while (in.hasNextLine() && k < modelsCount) {
                        try {
                            String[] nameAndPrice = in.nextLine().split(" ");
                            transport.addModel(nameAndPrice[0], Double.parseDouble(nameAndPrice[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        k++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case BINARY:
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
                    transport = (Transport) in.readObject();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return transport;
    }

    public void write(FileType fileType, Transport transport) {
        String filename = fileType.getFilename();

        switch (fileType) {
            case TEXT:
                try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
                    out.println(transport.getBrand());
                    int modelsCount = transport.getModelsCount();
                    out.println(modelsCount);
                    String[] names = transport.getModelsNames();
                    double[] prices = transport.getModelsPrices();
                    for (int i = 0; i < modelsCount; i++) {
                        out.println(names[i] + " " + prices[i]);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case BINARY:
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
                    out.writeObject(transport);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
