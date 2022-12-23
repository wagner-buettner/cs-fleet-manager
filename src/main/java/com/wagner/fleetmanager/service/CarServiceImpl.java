package com.wagner.fleetmanager.service;

import com.wagner.fleetmanager.exceptionhandler.LicensePlateDuplicated;
import com.wagner.fleetmanager.model.Car;
import com.wagner.fleetmanager.model.dto.CarDto;
import com.wagner.fleetmanager.repository.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService{

    private final CarRepository repository;

    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CarDto> createCars(List<Car> carList) {
        validateIfLicensePlateExists(carList);
        return carListToDtoList(repository.saveAll(carList));
    }

    @Override
    public List<CarDto> findAll() {
        return carListToDtoList(repository.findAll());
    }

    private CarDto modelToDto(Car car) {
        return modelMapper.map(car, CarDto.class);
    }
    private List<CarDto> carListToDtoList(List<Car> carList) {
        return carList.stream().map(this::modelToDto).collect(Collectors.toList());
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
