package ru.sergei.komarov.java.patterns.lab4;

import ru.sergei.komarov.java.patterns.lab1.factories.AutoFactory;
import ru.sergei.komarov.java.patterns.lab1.factories.MotoFactory;
import ru.sergei.komarov.java.patterns.lab1.factories.TransportFactory;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;
import ru.sergei.komarov.java.patterns.lab1.utils.TransportUtils;
import ru.sergei.komarov.java.patterns.lab4.dao.BinaryDAO;
import ru.sergei.komarov.java.patterns.lab4.dao.TextDAO;
import ru.sergei.komarov.java.patterns.lab4.dao.TransportDAO;

public class DAOTests {

    public static void main(String[] args) throws Exception {
        TransportDAO dao = new BinaryDAO();
        TransportFactory factory = new AutoFactory();
        Transport transport = null;
        Transport recoveredTransport = null;

        //Binary DAO + Car
        System.out.println("Binary DAO + Car");
        transport = factory.createInstance("CarBrand1", 5);
        dao.write(transport);
        recoveredTransport = dao.read();
        TransportUtils.printModelsNames(recoveredTransport);

        //Text DAO + Car
        System.out.println("Text DAO + Car");
        dao = new TextDAO();
        dao.write(transport);
        recoveredTransport = dao.read();
        TransportUtils.printModelsNames(recoveredTransport);

        //Binary DAO + Moto
        System.out.println("Binary DAO + Moto");
        factory = new MotoFactory();
        dao = new BinaryDAO();
        transport = factory.createInstance("MotoBrand1", 0);
        transport.addModel("moto1", 100);
        transport.addModel("moto2", 200);
        transport.addModel("moto3", 300);
        transport.addModel("moto4", 400);
        dao.write(transport);
        recoveredTransport = dao.read();
        TransportUtils.printModelsNames(recoveredTransport);

        //Text DAO + Moto
        System.out.println("Text DAO + Moto");
        dao = new TextDAO();
        dao.write(transport);
        recoveredTransport = dao.read();
        TransportUtils.printModelsNames(recoveredTransport);
    }
}
