package com.wagner.fleetmanager.service;

import com.wagner.fleetmanager.model.Car;

import java.util.List;

public interface CarService {
    List<Car> createCars(List<Car> carList);
    List<Car> findAll();
}
