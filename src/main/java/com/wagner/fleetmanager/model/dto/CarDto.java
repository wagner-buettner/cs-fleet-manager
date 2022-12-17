package com.wagner.fleetmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CarDto {

    private UUID id;

    private String brand;

    private String licensePlate;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;
}
