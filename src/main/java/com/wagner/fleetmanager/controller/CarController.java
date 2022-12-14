package com.wagner.fleetmanager.controller;

import com.wagner.fleetmanager.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class CarController {

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getAllCars() {
        var carList = new ArrayList<Car>();
        carList.add(
            new Car(
                UUID.randomUUID(),
                "Volkswagen",
                "L-CS8877E”",
                "Manufacturer",
                "Berlin",
                "available",
                LocalDateTime.now(),
                LocalDateTime.now()
            )
        );
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }
}
