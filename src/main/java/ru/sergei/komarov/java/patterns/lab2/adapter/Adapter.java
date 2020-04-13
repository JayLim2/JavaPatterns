package ru.sergei.komarov.java.patterns.lab2.adapter;

import java.io.*;

public class Adapter {

    public static void writeOutputStream(String[] strings, OutputStream outputStream) throws IOException {
        System.out.println("Запись данных...");
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeInt(strings.length);
        for (String string : strings) {
            dataOutputStream.writeInt(string.length());
            dataOutputStream.write(string.getBytes());
        }
        System.out.println("Данные записаны.");
    }

    public static String[] readInputStream(InputStream inputStream) throws IOException {
        System.out.println("Чтение данных...");
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String[] strings = new String[dataInputStream.readInt()];
        for (int i = 0; i < strings.length; i++) {
            byte[] string = new byte[dataInputStream.readInt()];
            for (int j = 0; j < string.length; j++) {
                string[j] = dataInputStream.readByte();
            }
            strings[i] = new String(string);
        }
        System.out.println("Данные прочитаны.");
        return strings;
    }
}