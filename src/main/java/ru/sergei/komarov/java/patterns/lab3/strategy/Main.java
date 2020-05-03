package ru.sergei.komarov.java.patterns.lab3.strategy;

public class Main {

    private static final String SOURCE_CORRECT = "src/main/resources/student_correct.xml";
    private static final String SOURCE_INCORRECT = "src/main/resources/student_incorrect.xml";
    private static final String RESULT = "src/main/resources/student_avg.xml";

    public static void main(String[] args) {
        System.out.println("DOM\n==========================");
        DOMAnalyzer domAnalyzer = new DOMAnalyzer();
        domAnalyzer.analyze(SOURCE_CORRECT, RESULT);
        System.out.println();
        domAnalyzer.analyze(SOURCE_INCORRECT, RESULT);
        System.out.println();

        System.out.println("SAX\n==========================");
        SAXAnalyzer saxAnalyzer = new SAXAnalyzer();
        saxAnalyzer.analyze(SOURCE_CORRECT, RESULT);
        System.out.println();
        saxAnalyzer.analyze(SOURCE_INCORRECT, RESULT);
    }
}
