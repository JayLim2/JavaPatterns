package ru.sergei.komarov.java.patterns.lab4;

import ru.sergei.komarov.java.patterns.lab1.factories.AutoFactory;
import ru.sergei.komarov.java.patterns.lab1.factories.TransportFactory;
import ru.sergei.komarov.java.patterns.lab1.models.Transport;
import ru.sergei.komarov.java.patterns.lab1.utils.TransportUtils;
import ru.sergei.komarov.java.patterns.lab4.dao.TransportDAO;

import static ru.sergei.komarov.java.patterns.lab4.dao.FileType.BINARY;
import static ru.sergei.komarov.java.patterns.lab4.dao.FileType.TEXT;

public class DAOTests {

    public static void main(String[] args) throws Exception {
        TransportDAO dao = new TransportDAO();
        TransportFactory factory = null;
        Transport transport = null;
        Transport recoveredTransport = null;

        //############### CARS ###################
        factory = new AutoFactory();
        transport = factory.createInstance("CarBrand1", 5);

        dao.write(TEXT, transport);
        recoveredTransport = dao.read(TEXT);
        TransportUtils.printModelsNames(recoveredTransport);

        System.out.println();

        transport.setBrand("SuperBrand2");
        transport.removeModel("car2");
        transport.addModel("car777", 100);
        dao.write(BINARY, transport);
        recoveredTransport = dao.read(BINARY);
        TransportUtils.printModelsNames(recoveredTransport);
    }
}
