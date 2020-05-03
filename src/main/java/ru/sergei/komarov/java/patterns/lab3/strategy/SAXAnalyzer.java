package ru.sergei.komarov.java.patterns.lab3.strategy;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.sergei.komarov.java.patterns.lab1.utils.PrintUtils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SAXAnalyzer implements Analyzer {
    private double actualAverage = 0;
    private double expectedAverage = 0;

    public void analyze(String filePath, String newFilePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new CustomHandler();
            saxParser.parse(filePath, handler);
            if (actualAverage != expectedAverage) {
                try {
                    Path inputPath = Paths.get(filePath);
                    String input = new String(Files.readAllBytes(inputPath));
                    input = input.replaceFirst(String.valueOf(actualAverage), String.valueOf(expectedAverage));
                    InputSource src = new InputSource(new StringReader(input));
                    Transformer tr = TransformerFactory.newInstance().newTransformer();
                    SAXSource source = new SAXSource(saxParser.getXMLReader(), src);
                    FileOutputStream fos = new FileOutputStream(newFilePath);
                    StreamResult result = new StreamResult(fos);
                    tr.transform(source, result);
                } catch (IOException | TransformerException | SAXException e) {
                    e.printStackTrace();
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private class CustomHandler extends DefaultHandler {
        double ranksSum = 0;
        double ranksCount = 0;
        boolean isAverageTagStarted = false;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            switch (qName) {
                case "subject":
                    ranksSum += Double.parseDouble(attributes.getValue("mark"));
                    ranksCount++;
                    break;
                case "average":
                    isAverageTagStarted = true;
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            if (isAverageTagStarted) {
                actualAverage = Double.parseDouble(new String(ch, start, length));
                PrintUtils.printAverageValidation(actualAverage, ranksSum, ranksCount);
                isAverageTagStarted = false;
            }
        }
    }
}