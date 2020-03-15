package ru.sergei.komarov.java.patterns.models;

import ru.sergei.komarov.java.patterns.exceptions.DuplicateModelNameException;
import ru.sergei.komarov.java.patterns.exceptions.ModelPriceOutOfBoundsException;
import ru.sergei.komarov.java.patterns.exceptions.NoSuchModelNameException;

import java.util.Arrays;
import java.util.Objects;

public class Car implements Transport {

    private String brand;
    private CarModel[] models;
    private int size;

    public Car(String brand, int modelsCount) {
        this.brand = brand;
        models = new CarModel[modelsCount];
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

        if (Objects.equals(oldName, newName)) {
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
        int index = getIndexByName(name);
        if (index == -1) {
            throw new NoSuchModelNameException(name);
        }
        models[index].price = newPrice;
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

        if (Objects.equals(price, Double.NaN) || Double.compare(price, 0) <= 0) {
            throw new ModelPriceOutOfBoundsException(Double.toString(price));
        }

        int modelsCount = getModelsCount();
        CarModel newModel = new CarModel(name, price);
        if (modelsCount < models.length) {
            models[modelsCount] = newModel;
        } else {
            CarModel[] newModels = Arrays.copyOf(models, modelsCount + 1);
            newModels[modelsCount] = newModel;
            models = newModels;
        }
        size++;
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

        if (modelsCount < models.length) {
            System.arraycopy(models, index + 1, models, index, modelsCount - index);
        } else {
            int newSize = modelsCount - 1;
            CarModel[] newModels;
            if (index == newSize) {
                newModels = Arrays.copyOf(models, newSize);
            } else {
                newModels = new CarModel[newSize];
                System.arraycopy(models, 0, newModels, 0, index);
                System.arraycopy(models, index + 1, newModels, index, newSize - index);
            }
            models = newModels;
        }
        size--;
    }

    /**
     * Количество моделей в массиве
     *
     * @return количество
     */
    public int getModelsCount() {
        return size;
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

    //##################################################################

    private static class CarModel {
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
}
