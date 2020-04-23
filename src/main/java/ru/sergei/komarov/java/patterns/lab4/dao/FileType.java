package ru.sergei.komarov.java.patterns.lab4.dao;

public enum FileType {
    BINARY("dao.bin"), TEXT("dao.txt");

    private String filename;

    FileType(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
