package com.wagner.fleetmanager.model;

import com.wagner.fleetmanager.model.enumerations.CarStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_car")
public class Car {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @ColumnDefault("gen_random_uuid()")
    @Type(type = "uuid-char")
    private UUID id;

    private String brand;

    @Column(unique = true)
    private String licensePlate;

    private String manufacturer;

    private String operationCity;

    private CarStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;
}
