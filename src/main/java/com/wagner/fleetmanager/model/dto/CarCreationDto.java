package com.wagner.fleetmanager.model.dto;

import com.sun.istack.NotNull;
import com.wagner.fleetmanager.model.enumerations.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CarCreationDto {

    @NotNull
    private String brand;

    @NotNull
    private String licensePlate;

    @NotNull
    private String manufacturer;

    @NotNull
    private String operationCity;

    @Enumerated
    @NotNull
    private CarStatus status;
}
