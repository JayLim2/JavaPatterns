package ru.sergei.komarov.java.patterns.lab3.strategy;

public class Main {

//    private static final String SOURCE_CORRECT = "src/main/resources/student_correct.xml";
//    private static final String SOURCE_INCORRECT = "src/main/resources/student_incorrect.xml";
//    private static final String RESULT = "src/main/resources/student_avg.xml";

    private static final String COMMON = "src/main/resources/";
    private static final String EXTENSION = ".xml";

    public static void main(String[] args) {
        String correct = COMMON + args[0] + EXTENSION;
        String incorrect = COMMON + args[1] + EXTENSION;
        String result = COMMON + args[2] + EXTENSION;

        if (false) {
            System.out.println("DOM\n==========================");
            DOMAnalyzer domAnalyzer = new DOMAnalyzer();
            domAnalyzer.analyze(correct, result);
            System.out.println();
            domAnalyzer.analyze(incorrect, result);
            System.out.println();
        }

        if (true) {
            System.out.println("SAX\n==========================");
            SAXAnalyzer saxAnalyzer = new SAXAnalyzer();
            saxAnalyzer.analyze(correct, result);
            System.out.println();
            saxAnalyzer.analyze(incorrect, result);
        }
    }
}
