package ru.sergei.komarov.java.patterns.lab3.strategy;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.sergei.komarov.java.patterns.lab1.utils.PrintUtils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

public class DOMAnalyzer implements Analyzer {

    @Override
    public void analyze(String filePath, String newFilePath) {
        double ranksSum = 0;
        double ranksCount = 0;
        double actualAverage = 0;
        double expectedAverage = 0;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(filePath);
            Node root = document.getDocumentElement();
            NodeList nodes = root.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                switch (node.getNodeName()) {
                    case "subject":
                        ranksSum += Integer.parseInt(node.getAttributes().getNamedItem("mark").getNodeValue());
                        ranksCount++;
                        break;
                    case "average":
                        actualAverage = Double.parseDouble(node.getFirstChild().getNodeValue());
                        break;
                }
            }
            PrintUtils.printAverageValidation(actualAverage, ranksSum, ranksCount);
            if (actualAverage != expectedAverage) {
                Node average = document.getElementsByTagName("average").item(0).getFirstChild();
                average.setNodeValue(String.valueOf(expectedAverage));
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                DOMSource source = new DOMSource(document);
                FileOutputStream fos = new FileOutputStream(newFilePath);
                StreamResult result = new StreamResult(fos);
                tr.transform(source, result);
            }
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
