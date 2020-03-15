package ru.sergei.komarov.java.patterns.models;

import ru.sergei.komarov.java.patterns.exceptions.DuplicateModelNameException;
import ru.sergei.komarov.java.patterns.exceptions.ModelPriceOutOfBoundsException;
import ru.sergei.komarov.java.patterns.exceptions.NoSuchModelNameException;

import java.util.Objects;

public class Motorcycle implements Transport {

    private String brand;
    private MotorcycleModel head;
    private int size;

    public Motorcycle(String brand) {
        head = new MotorcycleModel();
        head.prev = head;
        head.next = head;
        size = 0;
        this.brand = brand;
    }

    /**
     * Получить марку мотоциклов
     * @return название марки
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Изменить марку мотоциклов
     * @param brand новое название марки
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Изменения названия модели
     *
     * @param oldName старое название
     * @param newName новое название
     */
    public void setModelName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        if(oldName == null) {
            throw new NoSuchModelNameException(null);
        }

        if(newName == null) {
            throw new NullPointerException("Name must not be null.");
        }

        if(Objects.equals(oldName, newName)) {
            throw new DuplicateModelNameException(newName);
        }

        MotorcycleModel temp = head.next;
        while (temp != head) {
            if(Objects.equals(oldName, temp.name)) {
                temp.name = newName;
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * Получение массива названий моделей
     *
     * @return массив названий моделей
     */
    public String[] getModelsNames() {
        String[] names = new String[size];
        MotorcycleModel temp = head.next;
        int index = 0;
        while (temp != head) {
            names[index++] = temp.name;
            temp = temp.next;
        }
        return names;
    }

    /**
     * Получение массива цен моделей
     *
     * @return массив цен моделей
     */
    public double[] getModelsPrices() {
        double[] prices = new double[size];
        MotorcycleModel temp = head.next;
        int index = 0;
        while (temp != head) {
            prices[index++] = temp.price;
            temp = temp.next;
        }
        return prices;
    }

    /**
     * Получение цены модели по ее названию
     *
     * @param name название модели
     * @return цена, если есть модель с таким именем, или NaN, если такой модели нет
     */
    public double getPriceByName(String name) throws NoSuchModelNameException {
        if(name == null) {
            throw new NoSuchModelNameException(null);
        }

        MotorcycleModel temp = head.next;
        while (temp != head) {
            if (Objects.equals(temp.name, name)) {
                return temp.price;
            }
            temp = temp.next;
        }
        throw new NoSuchModelNameException(name);
    }

    /**
     * Установление цены модели по ее названию
     *
     * @param name     название модели
     * @param newPrice новая цена модели
     */
    public void setPriceByName(String name, double newPrice) throws NoSuchModelNameException {
        if(name == null) {
            throw new NoSuchModelNameException(null);
        }

        MotorcycleModel temp = head.next;
        while (temp != head) {
            if (Objects.equals(temp.name, name)) {
                temp.price = newPrice;
                return;
            }
            temp = temp.next;
        }
        throw new NoSuchModelNameException(name);
    }

    /**
     * Добавление новой модели в список
     *
     * @param name  название модели
     * @param price цена модели
     */
    public void addModel(String name, double price) throws DuplicateModelNameException {
        if(name == null) {
            throw new NullPointerException("Name must not be null.");
        }

        if (contains(name)) {
            throw new DuplicateModelNameException(name);
        }

        if(Objects.equals(price, Double.NaN) || Double.compare(price, 0) <= 0) {
            throw new ModelPriceOutOfBoundsException(Double.toString(price));
        }

        MotorcycleModel newModel = new MotorcycleModel(name, price, null, null);
        newModel.prev = head.prev;
        newModel.next = head;
        head.prev.next = newModel;
        head.prev = newModel;
        size++;
    }

    /**
     * Удаление модели из списка по названию
     *
     * @param name название модели
     */
    public void removeModel(String name) throws NoSuchModelNameException {
        if(size == 0) {
            throw new IndexOutOfBoundsException("size = 0");
        }

        if(name == null) {
            throw new NoSuchModelNameException(null);
        }

        MotorcycleModel temp = head.next;
        while (temp != head && !Objects.equals(temp.name, name)) {
            temp = temp.next;
        }
        if(temp != head) {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            temp.prev = null;
            temp.next = null;
            size--;
        }
    }

    /**
     * Количество моделей в списке
     *
     * @return количество
     */
    public int getModelsCount() {
        return size;
    }

    private boolean contains(String name) {
        MotorcycleModel temp = head.next;
        while (temp != head && !Objects.equals(temp.name, name)) {
            temp = temp.next;
        }
        return temp != head;
    }

    //##################################################################

    private static class MotorcycleModel {
        String name = null;
        double price = Double.NaN;
        MotorcycleModel prev = null;
        MotorcycleModel next = null;

        MotorcycleModel() {
        }

        MotorcycleModel(String name, double price,
                        MotorcycleModel prev, MotorcycleModel next) {
            this.name = name;
            this.price = price;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return "MotorcycleModel{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }
}
