package ru.sergei.komarov.java.patterns.lab1.utils;

import java.text.DecimalFormat;

public class Validators {

    public static double runAverageValidation(double actualAverage, double ranksSum, double ranksCount) {
        DecimalFormat f = new DecimalFormat("##.00");
        double expectedAverage = Double.parseDouble(f.format(ranksSum / ranksCount).replaceAll(",", "."));
        System.out.println(Double.compare(actualAverage, expectedAverage) == 0 ? "Average in XML is correct" : "Average in XML is not correct");
        System.out.println("Actual: " + actualAverage);
        System.out.println("Expected: " + expectedAverage);
        return expectedAverage;
    }

}
