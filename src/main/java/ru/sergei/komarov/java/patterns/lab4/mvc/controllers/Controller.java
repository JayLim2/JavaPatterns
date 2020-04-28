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
import javafx.util.StringConverter;
import ru.sergei.komarov.java.patterns.lab4.mvc.models.Point;

import java.util.Iterator;
import java.util.Optional;

/**
 * Controller (в паттерне Model-View-Controller)
 * <p>
 * Содержит логику управления отображением (View) данных (Model).
 * <p>
 * Фактически - занимается тем, что сообщает View, как и когда обновляться и на основе каких данных (Model).
 */
public class Controller {

    //константы
    private static final String X = "x";
    private static final String Y = "f(x)";
    private static final String Y_FIELD = "y";
    private static final int COLUMN_WIDTH = 106;

    //поля, необходимые для связывания контроллера с формой
    @FXML
    private TableView<Point> valuesTable;
    @FXML
    private LineChart<Double, Double> chart;
    @FXML
    private TextField xInputField;

    //данные, используемые контроллером для управления представлением
    private ObservableList<Point> points;
    private XYChart.Series<Double, Double> series = new XYChart.Series<>();
    private Point selectedPoint = null;

    /**
     * Первичная инициализация View и Model
     *
     * 1. Заполняем данные таблицы точками.
     * 2. Добавляем слушателя события "Выбрана строка таблицы", который сохраняет в поле selectedInput значение точки,
     * соответствующей выбранной строке.
     * 3. Программно создаем столбцы (см.комменты к методу createTableColumn()
     * 4. Добавляем точки в модель данных графика (обновляется график автоматически). См. комменты к addPoint()
     */
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

    /**
     * Обработчик события "Нажата кнопка 'Добавить точку'"
     *
     * Парсит значение текстового поля, в которое вводится координата x.
     * - Если происходит ошибка ИЛИ если такая точка уже есть - то вылетает сообщение.
     * - Если всё ок - поле ввода очищается, точка добавляется в таблицу и на график.
     */
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

    /**
     * Обработчик события "Нажата кнопка Delete на клавиатуре"
     *
     * Формально ловит любое нажатие на клавиатуре.
     * - Если нажата Delete и пользователь подтвердил в диалоговом окне намерение удалить строку то:
     * - - Если строка в таблице выбрана - происходит удаление из таблицы и с графика
     * - - Иначе вылетает сообщение "Перед удалением выберите точку в таблице"
     * - Иначе обработчик ничего не делает
     *
     * @param event
     */
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

    /**
     * Вспомогательный метод для добавления точки на график
     *
     * @param point модель точки
     */
    private void addPoint(Point point) {
        Double x = point.getX();
        Double y = point.getY();
        series.getData().add(new XYChart.Data<>(x, y));
    }

    /**
     * Вспомогательный метод для создания столбца таблицы с нужной конфигурацией.
     *
     * @param columns наблюдаемый (таблицей) список столбцов
     * @param name название столбца (видимое юзером)
     * @param modelFieldName название поля в модели Point, соответствующее данному столбцу
     * @param width ширина столбца (в пикселях)
     * @param isEditable true - редактируемый столбец, false - иначе
     */
    private void createTableColumn(ObservableList<TableColumn<Point, Double>> columns,
                                   String name, String modelFieldName,
                                   int width,
                                   boolean isEditable) {

        //Создаем столбец
        TableColumn<Point, Double> column = new TableColumn<>(name);
        //Создаем фабрику значений ячейки, которой указываем, из какого поля объекта типа Point брать значение
        column.setCellValueFactory(new PropertyValueFactory<>(modelFieldName));
        //Устанавливаем ширину столбца
        column.setPrefWidth(width);
        //Отключаем возможность сортировки столбца
        column.setSortable(false);
        if (isEditable) { //если включен режим редактирования (столбец х)
            //переопределяем конвертер вводимого значения таким образом,
            //чтобы он преобразовывал введенное значение в Double, либо выдавал 0.0, в случае если введено
            //например, abc
            column.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Double>() {
                @Override
                public String toString(Double object) {
                    try {
                        return Double.toString(object);
                    } catch (Exception ignored) {
                    }
                    return "0.0";
                }

                @Override
                public Double fromString(String string) {
                    try {
                        return Double.parseDouble(string);
                    } catch (Exception e) {
                        showError("Координаты должны быть числами.");
                    }
                    return 0.0;
                }
            }));
            //устанавливаем слушатель события "Редактирование ячейки завершено"
            column.setOnEditCommit(event -> {
                //извлекаем все необходимые данные в переменные
                int rowIndex = event.getTablePosition().getRow();
                int colIndex = event.getTablePosition().getColumn();
                Point newPoint = event.getRowValue();
                Double oldValue = event.getOldValue();
                Double newValue = event.getNewValue();

                //если столбец "х", то обновляем х в объекте Point
                if (colIndex == 0) {
                    newPoint.setX(newValue);
                }
                //заменяем Point в данных таблицы (наблюдаемый список значений)
                points.set(rowIndex, newPoint);

                //обновляем график: ищем нужную точку и заменяем ей значения
                //график перерисуется автоматически
                for (XYChart.Data<Double, Double> data : series.getData()) {
                    if (colIndex == 0 && Double.compare(data.getXValue(), oldValue) == 0) {
                        data.setXValue(newValue);
                        data.setYValue(newPoint.getY());
                        break;
                    }
                }
            });
        }
        columns.add(column);
    }

    /**
     * Вспомогательный метод для отображения сообщения ошибки.
     *
     * @param message текст ошибки
     */
    private static void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }

    /**
     * Вспомогательный метод для отображения вопроса пользователю
     *
     * @param message текст вопроса
     * @return true - пользователь нажал "Да", false - нажал "Нет"
     */
    private static boolean showConfirm(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        return buttonType.get() == ButtonType.YES;
    }

}