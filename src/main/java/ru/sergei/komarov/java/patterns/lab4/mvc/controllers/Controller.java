package ru.sergei.komarov.java.patterns.lab4.mvc.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.DoubleStringConverter;
import ru.sergei.komarov.java.patterns.lab4.mvc.models.Point;

import java.util.Iterator;
import java.util.Optional;

public class Controller {

    private static final String X = "x";
    private static final String Y = "f(x)";
    private static final String Y_FIELD = "y";
    private static final int COLUMN_WIDTH = 106;

    @FXML
    private TableView<Point> valuesTable;
    @FXML
    private LineChart<Double, Double> chart;
    @FXML
    private TextField xInputField;

    private ObservableList<Point> points;
    private XYChart.Series<Double, Double> series = new XYChart.Series<>();
    private Point selectedPoint = null;

    @FXML
    public void initialize() {
        //Values table
        points = FXCollections.observableArrayList(
                new Point(1.0),
                new Point(1.5),
                new Point(3.2),
                new Point(6.6),
                new Point(11.1)
        );
        valuesTable.setItems(points);
        valuesTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selectedPoint = newValue
        );

        ObservableList<TableColumn<Point, Double>> columns = FXCollections.observableArrayList();
        createTableColumn(columns, X, X, COLUMN_WIDTH, true);
        createTableColumn(columns, Y, Y_FIELD, COLUMN_WIDTH, false);
        valuesTable.getColumns().addAll(columns);

        //Graphs
        for (Point point : points) {
            addPoint(point);
        }
        chart.getData().add(series);
    }

    @FXML
    public void onAddButtonClick() {
        try {
            Double x = Double.parseDouble(xInputField.getText());

            Point point = new Point(x);
            if (points.contains(point)) {
                showError("Точка с такими координатами уже существует.");
            } else {
                points.add(point);
                xInputField.clear();
                addPoint(point);
            }
        } catch (Exception e) {
            showError("Координаты должны быть числами.");
        }
    }

    @FXML
    public void onDeletePressed(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE && showConfirm("Вы действительно хотите удалить эту точку?")) {
            if (selectedPoint == null) {
                showError("Перед удалением выберите точку в таблице.");
            } else {
                double selectedX = selectedPoint.getX();
                double selectedY = selectedPoint.getY();
                points.remove(selectedPoint);
                ObservableList<XYChart.Data<Double, Double>> chartPoints = series.getData();
                Iterator<XYChart.Data<Double, Double>> chartPointsIterator = chartPoints.iterator();
                while (chartPointsIterator.hasNext()) {
                    XYChart.Data<Double, Double> current = chartPointsIterator.next();
                    if (Double.compare(current.getXValue(), selectedX) == 0 && Double.compare(current.getYValue(), selectedY) == 0) {
                        chartPointsIterator.remove();
                        return;
                    }
                }
            }
        }
    }

    private void addPoint(Point point) {
        Double x = point.getX();
        Double y = point.getY();
        series.getData().add(new XYChart.Data<>(x, y));
    }

    private void createTableColumn(ObservableList<TableColumn<Point, Double>> columns,
                                   String name, String modelFieldName,
                                   int width,
                                   boolean isEditable) {

        TableColumn<Point, Double> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(modelFieldName));
        column.setPrefWidth(width);
        column.setSortable(false);
        if (isEditable) {
            column.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            column.setOnEditCommit(event -> {
                //extracting data
                int rowIndex = event.getTablePosition().getRow();
                int colIndex = event.getTablePosition().getColumn();
                Point newPoint = event.getRowValue();
                Double oldValue = event.getOldValue();
                Double newValue = event.getNewValue();

                //table observable list update
                if (colIndex == 0) {
                    newPoint.setX(newValue);
                }
                points.set(rowIndex, newPoint);

                //chart update
                for (XYChart.Data<Double, Double> data : series.getData()) {
                    if (colIndex == 0 && Double.compare(data.getXValue(), oldValue) == 0) {
                        data.setXValue(newValue);
                    }
                    if (colIndex == 1 && Double.compare(data.getYValue(), oldValue) == 0) {
                        data.setYValue(newValue);
                    }
                }
            });
        }
        columns.add(column);
    }

    private static void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }

    private static boolean showConfirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.get() == ButtonType.YES;
    }

}