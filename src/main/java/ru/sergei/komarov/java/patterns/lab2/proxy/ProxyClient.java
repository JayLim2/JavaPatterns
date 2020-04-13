package ru.sergei.komarov.java.patterns.lab2.proxy;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ProxyClient {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public double multiply(double a, double b) {
        double result = 0;
        try {
            socket = new Socket("localhost", 5000);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());

            dataOutputStream.writeDouble(a);
            dataOutputStream.writeDouble(b);

            result = dataInputStream.readDouble();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                dataInputStream.close();
                dataOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}