package com.wagner.fleetmanager.repository;

import com.wagner.fleetmanager.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
}
