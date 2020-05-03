package ru.sergei.komarov.java.patterns.lab1.utils;

import java.text.DecimalFormat;

public class PrintUtils {

    public static void printAverageValidation(double actualAverage, double ranksSum, double ranksCount) {
        DecimalFormat f = new DecimalFormat("##.00");
        double expectedAverage = Double.parseDouble(f.format(ranksSum / ranksCount).replaceAll(",", "."));
        System.out.println(actualAverage == expectedAverage ? "Average in XML is correct" : "Average in XML is not correct");
        System.out.println("Actual: " + actualAverage);
        System.out.println("Expected: " + expectedAverage);
    }

}
