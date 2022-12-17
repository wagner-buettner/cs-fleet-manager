package com.wagner.fleetmanager.service;

import com.wagner.fleetmanager.exceptionhandler.LicensePlateDuplicated;
import com.wagner.fleetmanager.model.Car;
import com.wagner.fleetmanager.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository repository;

    public CarServiceImpl(CarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Car> createCars(List<Car> carList) {
        validateIfLicensePlateExists(carList);
        return repository.saveAll(carList);
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }

    private void validateIfLicensePlateExists(List<Car> carList) {
        carList.stream()
            .filter(car -> repository.existsCarsByLicensePlate(car.getLicensePlate()))
            .findAny()
            .ifPresent(car -> {
                throw new LicensePlateDuplicated(car.getLicensePlate());
            });
    }
}
