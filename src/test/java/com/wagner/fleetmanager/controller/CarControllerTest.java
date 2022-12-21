package com.wagner.fleetmanager.controller;

import com.wagner.fleetmanager.AbstractTest;
import com.wagner.fleetmanager.model.Car;
import com.wagner.fleetmanager.model.dto.CarDto;
import com.wagner.fleetmanager.model.enumerations.CarStatus;
import com.wagner.fleetmanager.service.CarService;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CarController.class)
@ExtendWith(SpringExtension.class)
class CarControllerTest extends AbstractTest {

    @MockBean
    CarService carService;

    @Autowired
    private CarController controller;

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void findAllCarsTest() throws Exception {
        given(carService.findAll())
            .willReturn(List.of(
                new CarDto(
                    UUID.randomUUID(),
                    "BMW",
                    "B-CS2255E",
                    CarStatus.AVAILABLE,
                    LocalDateTime.now(),
                    LocalDateTime.now()
                ),
                new CarDto(
                    UUID.randomUUID(),
                    "BMW",
                    "B-CS1177E",
                    CarStatus.AVAILABLE,
                    LocalDateTime.now(),
                    LocalDateTime.now()
                )));

        mvc.perform(get("/v1/cars"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].licensePlate").value("B-CS2255E"))
            .andExpect(jsonPath("$[1].licensePlate").value("B-CS1177E"));
        verify(carService).findAll();
    }

    @Test
    public void createTwoCarsTest() throws Exception {
        String uri = "/v1/cars";

        List<Car> carListToCreate = getCarListToCreate();
        String inputJson = super.mapToJson(carListToCreate);

        List<CarDto> carListDto = getCarDtoList();
        given(carService.createCars(any())).willReturn(carListDto);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$[0].licensePlate", is("V-CS1122E")))
            .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @NotNull
    private static List<CarDto> getCarDtoList() {
        CarDto carDto1 = new CarDto();
        carDto1.setBrand("Volkswagen");
        carDto1.setLicensePlate("V-CS1122E");
        carDto1.setStatus(CarStatus.AVAILABLE);
        carDto1.setCreatedAt(LocalDateTime.now());
        carDto1.setLastUpdatedAt(LocalDateTime.now());

        CarDto carDto2 = new CarDto();
        carDto2.setBrand("Volkswagen");
        carDto2.setLicensePlate("V-CS3344E");
        carDto2.setStatus(CarStatus.AVAILABLE);
        carDto2.setCreatedAt(LocalDateTime.now());
        carDto2.setLastUpdatedAt(LocalDateTime.now());

        List<CarDto> carListDto = List.of(carDto1, carDto2);
        return carListDto;
    }

    @NotNull
    private static List<Car> getCarListToCreate() {
        Car car1 = new Car();
        car1.setId(null);
        car1.setBrand("Volkswagen");
        car1.setLicensePlate("V-CS1122E");
        car1.setManufacturer("Hamburger Manufacturer");
        car1.setOperationCity("Hamburg");
        car1.setStatus(CarStatus.AVAILABLE);
        car1.setCreatedAt(LocalDateTime.now());
        car1.setLastUpdatedAt(LocalDateTime.now());

        Car car2 = new Car();
        car2.setId(null);
        car2.setBrand("Volkswagen");
        car2.setLicensePlate("V-CS3344E");
        car2.setManufacturer("Hamburger Manufacturer");
        car2.setOperationCity("Hamburg");
        car2.setStatus(CarStatus.AVAILABLE);
        car2.setCreatedAt(LocalDateTime.now());
        car2.setLastUpdatedAt(LocalDateTime.now());

        List<Car> carListToCreate = List.of(car1, car2);
        return carListToCreate;
    }

}
