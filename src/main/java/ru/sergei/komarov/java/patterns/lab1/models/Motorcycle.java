package ru.sergei.komarov.java.patterns.lab1.models;

import ru.sergei.komarov.java.patterns.lab1.exceptions.DuplicateModelNameException;
import ru.sergei.komarov.java.patterns.lab1.exceptions.ModelPriceOutOfBoundsException;
import ru.sergei.komarov.java.patterns.lab1.exceptions.NoSuchModelNameException;
import ru.sergei.komarov.java.patterns.lab3.visitor.Visitor;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Objects;

public class Motorcycle implements Transport, Iterable<Motorcycle.MotorcycleModel> {

    private String brand;
    private MotorcycleModel head;
    private int size;

    {
        head = new MotorcycleModel();
        head.prev = head;
        head.next = head;
        size = 0; //как в методичке :)
    }

    public Motorcycle(String brand) {
        this.brand = brand;
    }

    /**
     * Получить марку мотоциклов
     *
     * @return название марки
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Изменить марку мотоциклов
     *
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
        if (oldName == null) {
            throw new NoSuchModelNameException(null);
        }

        if (newName == null) {
            throw new NullPointerException("Name must not be null.");
        }

        if (Objects.equals(oldName, newName) || contains(newName)) {
            throw new DuplicateModelNameException(newName);
        }

        MotorcycleModel temp = head.next;
        while (temp != head) {
            if (Objects.equals(oldName, temp.name)) {
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
        if (name == null) {
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
        if (name == null) {
            throw new NoSuchModelNameException(null);
        }

        if (isInvalidPrice(newPrice)) {
            throw new ModelPriceOutOfBoundsException(Double.toString(newPrice));
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
        if (name == null) {
            throw new NullPointerException("Name must not be null.");
        }

        if (contains(name)) {
            throw new DuplicateModelNameException(name);
        }

        if (isInvalidPrice(price)) {
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
        if (size == 0) {
            throw new IndexOutOfBoundsException("size = 0");
        }

        if (name == null) {
            throw new NoSuchModelNameException(null);
        }

        MotorcycleModel temp = head.next;
        while (temp != head && !Objects.equals(temp.name, name)) {
            temp = temp.next;
        }
        if (temp != head) {
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

    private boolean isInvalidPrice(double price) {
        return Objects.equals(price, Double.NaN) || Double.compare(price, 0) <= 0;
    }

    //#################################################################

    @Override
    public Object clone() throws CloneNotSupportedException {
        Motorcycle clonedMotorcycle = (Motorcycle) super.clone();

        MotorcycleModel newHead = new MotorcycleModel();
        newHead.next = newHead;
        newHead.prev = newHead;

        MotorcycleModel temp = head.next;
        while (temp != head) {
            MotorcycleModel newModel = new MotorcycleModel(temp.name, temp.price, null, null);
            newModel.prev = newHead.prev;
            newModel.next = newHead;
            newHead.prev.next = newModel;
            newHead.prev = newModel;
            temp = temp.next;
        }
        clonedMotorcycle.head = newHead;

        return clonedMotorcycle;
    }

    //##################################################################

    static class MotorcycleModel implements Serializable {
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

    //###################### ITERATOR #######################

    private class MotorcycleIterator implements Iterator<MotorcycleModel> {

        private MotorcycleModel temp = head.next;

        @Override
        public boolean hasNext() {
            return temp != head;
        }

        @Override
        public MotorcycleModel next() {
            temp = temp.next;
            return temp.prev;
        }
    }

    @Override
    public Iterator<MotorcycleModel> iterator() {
        return new MotorcycleIterator();
    }

    //###################### VISITOR #######################

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(new Motorcycle(brand)); //TOOD maybe add size?
    }
}
