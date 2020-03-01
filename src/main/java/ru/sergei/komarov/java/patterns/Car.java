package ru.sergei.komarov.java.patterns;

import java.util.Arrays;
import java.util.Objects;

public class Car {

    private String brand;
    private CarModel[] models;

    public Car(String brand, int modelsCount) {
        this.brand = brand;
        models = new CarModel[modelsCount];
    }

    /**
     * Получить марку автомобилей
     * @return название марки
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Изменить марку автомобилей
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
    public void setModelName(String oldName, String newName) {
        if (oldName != null && newName != null && !Objects.equals(oldName, newName)) {
            int index = getIndexByName(oldName);
            if (index != -1) {
                models[index].name = newName;
            }
        }
    }

    /**
     * Получение массива названий моделей
     *
     * @return массив названий моделей
     */
    public String[] getModelsNames() {
        if (models != null) {
            int modelsCount = getModelsCount();
            String[] names = new String[modelsCount];
            for (int i = 0; i < modelsCount; i++) {
                names[i] = models[i].name;
            }
            return names;
        }
        return null;
    }

    /**
     * Получение массива цен моделей
     *
     * @return массив цен моделей
     */
    public double[] getModelPrices() {
        if (models != null) {
            int modelsCount = getModelsCount();
            double[] prices = new double[models.length];
            for (int i = 0; i < models.length; i++) {
                prices[i] = models[i].price;
            }
            return prices;
        }
        return null;
    }

    /**
     * Получение цены модели по ее названию
     *
     * @param name название модели
     * @return цена, если есть модель с таким именем, или NaN, если такой модели нет
     */
    public double getPriceByName(String name) {
        int index = getIndexByName(name);
        if (index != -1) {
            return models[index].price;
        }
        return Double.NaN;
    }

    /**
     * Установление цены модели по ее названию
     *
     * @param name     название модели
     * @param newPrice новая цена модели
     */
    public void setPriceByName(String name, double newPrice) {
        int index = getIndexByName(name);
        if (index != -1) {
            models[index].price = newPrice;
        }
    }

    /**
     * Добавление новой модели в массив
     *
     * @param name  название модели
     * @param price цена модели
     */
    public void addModel(String name, double price) {
        if (models != null && name != null && !Objects.equals(price, Double.NaN) && price > 0) {
            CarModel[] newModels = Arrays.copyOf(models, models.length + 1);
            CarModel newModel = new CarModel(name, price);
            newModels[newModels.length - 1] = newModel;
            models = newModels;
        }
    }

    /**
     * Удаление модели из массива по названию
     *
     * @param name название модели
     */
    public void removeModel(String name) {
        int modelsCount = getModelsCount();
        int index = getIndexByName(name);
        if (index != -1) {
            int newSize = modelsCount - 1;
            CarModel[] newModels;
            if (index == newSize) {
                newModels = Arrays.copyOf(models, newSize);
            } else {
                newModels = new CarModel[newSize];
                int newIndex = index + 1;
                System.arraycopy(models, 0, newModels, 0, index);
                System.arraycopy(models, newIndex, newModels, newIndex, modelsCount - newIndex);
            }
            models = newModels;
        }
    }

    /**
     * Количество моделей в массиве
     *
     * @return количество
     */
    public int getModelsCount() {
        return models != null ? models.length : -1;
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
        if (models != null && name != null) {
            for (int i = 0; i < getModelsCount() && index < 0; i++) {
                if (equalsModelName(models[i], name)) {
                    index = i;
                }
            }
        }
        return index;
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

    }
}
