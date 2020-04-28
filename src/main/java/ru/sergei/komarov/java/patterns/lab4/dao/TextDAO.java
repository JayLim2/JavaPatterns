package ru.sergei.komarov.java.patterns.lab4.dao;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;
import ru.sergei.komarov.java.patterns.lab1.utils.TransportUtils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextDAO implements TransportDAO {

    private String filename;

    public TextDAO() {
        this.filename = "dao.txt";
    }

    public TextDAO(String filename) {
        this.filename = filename;
    }

    @Override
    public Transport read() {
        String brand = null;
        int modelsCount = 0;
        Transport transport = null;
        int k = 0;
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
        return transport;
    }

    @Override
    public void write(Transport transport) {
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
    }
}
