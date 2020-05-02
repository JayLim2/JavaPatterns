package ru.sergei.komarov.java.patterns.lab1.models;

/**
 * Тесты для итератора
 * <p>
 * Помещены в пакет models из-за того, что у моделей нет интерфейса и доступ типа package
 */
public class IteratorTests {
    public static void main(String[] args) throws Exception {
        Car cars = new Car("CarBrand", 5);
        Motorcycle motorcycles = new Motorcycle("MotoBrand");
        motorcycles.addModel("moto1", 100);
        motorcycles.addModel("moto2", 100);
        motorcycles.addModel("moto3", 100);

        //Cars
        for (Car.CarModel model : cars) {
            System.out.println(model);
        }

        System.out.println();

        //Moto
        for (Motorcycle.MotorcycleModel model : motorcycles) {
            System.out.println(model);
        }

    }
}
