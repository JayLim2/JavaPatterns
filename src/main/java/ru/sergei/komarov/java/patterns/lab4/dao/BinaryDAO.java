package ru.sergei.komarov.java.patterns.lab4.dao;

import ru.sergei.komarov.java.patterns.lab1.models.Transport;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BinaryDAO implements TransportDAO {

    private String filename;

    public BinaryDAO() {
        this.filename = "dao.bin";
    }

    public BinaryDAO(String filename) {
        this.filename = filename;
    }

    @Override
    public Transport read() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Transport) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(Transport transport) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(transport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
