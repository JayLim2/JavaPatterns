package ru.sergei.komarov.java.patterns.lab4.mvc.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ru.sergei.komarov.java.patterns.lab4.mvc.models.Point;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Controller {

    private static final String X = "x";
    private static final String Y = "f(x)";
    private static final String Y_FIELD = "y";
    private static final int COLUMN_WIDTH = 106;

    @FXML
    private TableView<Point> valuesTable;
    @FXML
    private LineChart<String, Double> chart;
    @FXML
    private TextField xInputField;
    @FXML
    private TextField yInputField;

    private ObservableList<Point> points;
    private XYChart.Series<String, Double> series = new XYChart.Series<>();

    private DecimalFormat decimalFormat;

    @FXML
    public void initialize() {
        //Utils
        decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
        decimalFormat.applyPattern("0.0");

        //Values table
        points = FXCollections.observableArrayList(
                new Point(1.0, 2.0),
                new Point(1.5, 4.0),
                new Point(2.0, 7.3),
                new Point(3.2, 5.9),
                new Point(7.1, 7.1)
        );
        valuesTable.setItems(points);

        ObservableList<TableColumn<Point, Double>> columns = FXCollections.observableArrayList();
        createTableColumn(columns, X, X, COLUMN_WIDTH);
        createTableColumn(columns, Y, Y_FIELD, COLUMN_WIDTH);
        valuesTable.getColumns().addAll(columns);

        //Graphs
        for (Point point : points) {
            addPoint(point);
        }
        chart.getData().add(series);
    }

    @FXML
    public void onAddButtonClick(ActionEvent event) {
        Double x = Double.NaN;
        Double y = Double.NaN;
        try {
            x = Double.parseDouble(xInputField.getText());
            y = Double.parseDouble(yInputField.getText());
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Введите корректные числа x и f(x).").show();
        }
        if (!Double.isNaN(x) && !Double.isNaN(y)) {
            Point point = new Point(x, y);
            points.add(point);
            xInputField.clear();
            yInputField.clear();
            addPoint(point);
        }
    }

    private void addPoint(Point point) {
        Double x = point.getX();
        Double y = point.getY();
        String xStr = decimalFormat.format(x);
        series.getData().add(new XYChart.Data<>(xStr, y));
    }

    private void createTableColumn(ObservableList<TableColumn<Point, Double>> columns,
                                   String name, String modelFieldName,
                                   int width) {

        TableColumn<Point, Double> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(modelFieldName));
        column.setPrefWidth(width);
        columns.add(column);
    }

}