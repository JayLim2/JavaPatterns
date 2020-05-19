package ru.sergei.komarov.java.patterns.lab1.models;

import ru.sergei.komarov.java.patterns.lab1.exceptions.DuplicateModelNameException;
import ru.sergei.komarov.java.patterns.lab1.exceptions.ModelPriceOutOfBoundsException;
import ru.sergei.komarov.java.patterns.lab1.exceptions.NoSuchModelNameException;
import ru.sergei.komarov.java.patterns.lab3.command.Command;
import ru.sergei.komarov.java.patterns.lab3.command.CommandPrintRow;
import ru.sergei.komarov.java.patterns.lab3.visitor.Visitor;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class Car implements Iterable<Car.CarModel>, Transport {

    private String brand;
    private CarModel[] models;

    public Car(String brand, int modelsCount) {
        this.brand = brand;

        //initialize array
        if (modelsCount < 0) {
            modelsCount = 0;
        }
        models = new CarModel[modelsCount];
        for (int i = 0; i < modelsCount; i++) {
            models[i] = new CarModel("car" + i, 100 * (i + 1));
        }
    }

    /**
     * Получить марку автомобилей
     *
     * @return название марки
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Изменить марку автомобилей
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

        int index = getIndexByName(oldName);
        if (index == -1) {
            throw new NoSuchModelNameException(oldName);
        }
        models[index].name = newName;
    }

    /**
     * Получение массива названий моделей
     *
     * @return массив названий моделей
     */
    public String[] getModelsNames() {
        int modelsCount = getModelsCount();
        String[] names = new String[modelsCount];
        for (int i = 0; i < modelsCount; i++) {
            names[i] = models[i].name;
        }
        return names;
    }

    /**
     * Получение массива цен моделей
     *
     * @return массив цен моделей
     */
    public double[] getModelsPrices() {
        int modelsCount = getModelsCount();
        double[] prices = new double[modelsCount];
        for (int i = 0; i < modelsCount; i++) {
            prices[i] = models[i].price;
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
        int index = getIndexByName(name);
        if (index != -1) {
            return models[index].price;
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
        if (!isInvalidPrice(newPrice)) {
            int index = getIndexByName(name);
            if (index == -1) {
                throw new NoSuchModelNameException(name);
            }

            models[index].price = newPrice;
        } else {
            throw new ModelPriceOutOfBoundsException(Double.toString(newPrice));
        }
    }

    /**
     * Добавление новой модели в массив
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

        int modelsCount = getModelsCount();
        models = Arrays.copyOf(models, modelsCount + 1);
        models[modelsCount] = new CarModel(name, price);
    }

    /**
     * Удаление модели из массива по названию
     *
     * @param name название модели
     */
    public void removeModel(String name) throws NoSuchModelNameException {
        int modelsCount = getModelsCount();
        if (modelsCount == 0) {
            throw new ArrayIndexOutOfBoundsException("size = 0");
        }

        int index = getIndexByName(name);
        if (index == -1) {
            throw new NoSuchModelNameException(name);
        }

        System.arraycopy(models, index + 1, models, index, modelsCount - index - 1);
        models = Arrays.copyOf(models, modelsCount - 1);
    }

    /**
     * Количество моделей в массиве
     *
     * @return количество
     */
    public int getModelsCount() {
        return models.length;
    }

    //#################################################################

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car clonedCar = (Car) super.clone();
        clonedCar.models = clonedCar.models.clone();
        int size = getModelsCount();
        for (int i = 0; i < size; i++) {
            CarModel currentModel = models[i];
            clonedCar.models[i] = new CarModel(currentModel.name, currentModel.price);
        }
        return clonedCar;
    }

    //########################### SERVICE METHODS ######################

    /**
     * Получения индекса в массиве моделей по названию модели
     *
     * @param name название модели
     * @return индекс массива, если есть модель с таким именем, или -1, если такой модели нет
     */
    private int getIndexByName(String name) {
        int index = -1;
        if (name != null) {
            for (int i = 0; i < getModelsCount() && index < 0; i++) {
                if (equalsModelName(models[i], name)) {
                    index = i;
                }
            }
        }
        return index;
    }

    private boolean contains(String name) {
        return getIndexByName(name) != -1;
    }

    private boolean equalsModelName(CarModel currentModel, String name) {
        return Objects.equals(currentModel.name, name);
    }

    private boolean isInvalidPrice(double price) {
        return Objects.equals(price, Double.NaN) || Double.compare(price, 0) <= 0;
    }

    //##################################################################

    static class CarModel implements Serializable {
        String name;
        double price;

        CarModel(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "CarModel{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    //###################### ITERATOR #######################

    private class CarIterator implements Iterator<CarModel> {

        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < getModelsCount();
        }

        @Override
        public CarModel next() {
            return models[counter++];
        }
    }

    @Override
    public Iterator<CarModel> iterator() {

        return new CarIterator();
    }

    //###################### COMMAND #######################

    private transient Command command = new CommandPrintRow();

    public void setPrintCommand(Command command) {
        this.command = command;
    }

    public void print(FileWriter fileWriter) {
        command.printToFile(new Car(brand, getModelsCount()), fileWriter);
    }

    //###################### VISITOR #######################

    @Override
    public void accept(Visitor visitor) {
        //visitor.visit(new Car(brand, getModelsCount()));
        visitor.visit(this);
    }

    //##################### MEMENTO ########################

    private transient Memento memento = new Memento();

    public void createMemento() {
        memento.setAuto(this);
    }

    public Car setMemento() {
        return memento.getAuto();
    }

    public static class Memento {

        byte[] savedState;

        void setAuto(Car car) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
                out.writeObject(car);
                out.flush();
                savedState = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Car getAuto() {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(savedState);
                ObjectInputStream in = new ObjectInputStream(bis);
                Car car = (Car) in.readObject();
                in.close();
                return car;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}