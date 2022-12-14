package com.wagner.fleetmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    private UUID id;

    private String brand;

    private String licensePlate;

    private String manufacturer;

    private String operationCity;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;
}
