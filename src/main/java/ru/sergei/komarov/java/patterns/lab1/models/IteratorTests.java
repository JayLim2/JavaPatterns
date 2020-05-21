package ru.sergei.komarov.java.patterns.lab1.models;

import java.util.Iterator;

/**
 * Тесты для итератора
 * <p>
 * Помещены в пакет models из-за того, что у моделей нет интерфейса и доступ типа package
 */
public class IteratorTests {

    public static void main(String[] args) throws Exception {
        Car cars = new Car("CarBrand", 5);
        Iterator<Car.CarModel> iterator = cars.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
