package com.wagner.fleetmanager.controller;

import com.wagner.fleetmanager.model.Car;
import com.wagner.fleetmanager.model.dto.CarDto;
import com.wagner.fleetmanager.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CarController {

    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> findAllCars() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/cars")
    public ResponseEntity<List<CarDto>> createCar(@RequestBody List<Car> carList) {
        return new ResponseEntity<>(service.createCars(carList), HttpStatus.CREATED);
    }
}
