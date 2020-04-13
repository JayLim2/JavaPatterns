package ru.sergei.komarov.java.patterns.lab2;

import ru.sergei.komarov.java.patterns.lab2.adapter.Adapter;
import ru.sergei.komarov.java.patterns.lab2.proxy.ProxyClient;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        //testAdapter();

        testProxy();
    }

    private static void testAdapter() throws Exception {
        String[] testArray = new String[]{"Hello", "World"};

        final String fileName = "data.bin";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        FileInputStream fileInputStream = new FileInputStream(fileName);

        Adapter.writeOutputStream(testArray, fileOutputStream);
        fileOutputStream.close();

        String[] strings = Adapter.readInputStream(fileInputStream);
        for (String string : strings) {
            System.out.println(string);
        }
        fileInputStream.close();
    }

    private static void testProxy() {
        ProxyClient proxyClient = new ProxyClient();
        System.out.println(proxyClient.multiply(123, 45));
    }
}