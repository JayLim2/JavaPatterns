package ru.sergei.komarov.java.patterns.lab3.strategy;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.sergei.komarov.java.patterns.lab1.utils.Validators;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

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
            expectedAverage = Validators.runAverageValidation(actualAverage, ranksSum, ranksCount);
            if (Double.compare(actualAverage, expectedAverage) != 0) {
                Node average = document.getElementsByTagName("average").item(0).getFirstChild();
                average.setNodeValue(Double.toString(expectedAverage));
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream out = new FileOutputStream(newFilePath);
            StreamResult result = new StreamResult(out);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
