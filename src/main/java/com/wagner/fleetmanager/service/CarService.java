package com.wagner.fleetmanager.service;

import com.wagner.fleetmanager.model.Car;
import com.wagner.fleetmanager.model.dto.CarDto;

import java.util.List;

public interface CarService {
    List<CarDto> createCars(List<Car> carList);
    List<CarDto> findAll();
}
