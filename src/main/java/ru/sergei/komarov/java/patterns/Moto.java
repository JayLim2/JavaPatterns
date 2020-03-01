package ru.sergei.komarov.java.patterns;

import java.util.Objects;

public class Moto {

    private String brand;
    private MotorcycleModel head;
    private int size;

    public Moto() {
        head = new MotorcycleModel();
        head.prev = head;
        head.next = head;
        size = 0;
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
    public void setModelName(String oldName, String newName) {
        if (oldName != null && newName != null && !Objects.equals(oldName, newName)) {
            MotorcycleModel temp = head.next;
            while (temp != head && !Objects.equals(oldName, newName)) {
                temp = temp.next;
            }
            if(temp != head) {
                temp.name = newName;
            }
        }
    }

    /**
     * Получение массива названий моделей
     *
     * @return массив названий моделей
     */
    public String[] getModelsNames() {
        if (size > 0) {
            String[] names = new String[size];
            MotorcycleModel temp = head.next;
            int index = 0;
            while (temp != head) {
                names[index] = temp.name;
                temp = temp.next;
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
        if (size > 0) {
            double[] prices = new double[size];
            MotorcycleModel temp = head.next;
            int index = 0;
            while (temp != head) {
                prices[index] = temp.price;
                temp = temp.next;
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
        if (name != null) {
            MotorcycleModel temp = head.next;
            while (temp != head) {
                if (Objects.equals(temp.name, name)) {
                    return temp.price;
                }
                temp = temp.next;
            }
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
        if (name != null) {
            MotorcycleModel temp = head.next;
            while (temp != head) {
                if (Objects.equals(temp.name, name)) {
                    temp.price = newPrice;
                    return;
                }
                temp = temp.next;
            }
        }
    }

    /**
     * Добавление новой модели в список
     *
     * @param name  название модели
     * @param price цена модели
     */
    public void addModel(String name, double price) {
        if(name != null && !Objects.equals(price, Double.NaN) && price > 0) {
            MotorcycleModel newModel = new MotorcycleModel(name, price, null, null);
            newModel.prev = head.prev;
            newModel.next = head;
            head.prev.next = newModel;
            head.prev = newModel;
            size++;
        }
    }

    /**
     * Удаление модели из списка по названию
     *
     * @param name название модели
     */
    public void removeModel(String name) {
        if(size > 0 && name != null) {
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
    }

    /**
     * Количество моделей в списке
     *
     * @return количество
     */
    public int getModelsCount() {
        return size;
    }

    /**
     * Получение элемента списка по индексу
     *
     * @param index индекс в диапазоне [0..size)
     * @return элемент по индексу
     */
    private MotorcycleModel get(int index) {
        if (index >= 0 && index < size && head.prev != head) {

            int i = 0;
            MotorcycleModel temp = head.next;
            while (temp != head && i != index) {
                i++;
                temp = temp.next;
            }
            return temp;
        }
        throw new IndexOutOfBoundsException("No element with index " + index);
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
    }
}
