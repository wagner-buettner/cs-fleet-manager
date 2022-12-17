package com.wagner.fleetmanager.controller;

import com.wagner.fleetmanager.AbstractTest;
import com.wagner.fleetmanager.model.Car;
import com.wagner.fleetmanager.model.dto.CarDto;
import com.wagner.fleetmanager.service.CarService;
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
                    "available",
                    LocalDateTime.now(),
                    LocalDateTime.now()
                ),
                new CarDto(
                    UUID.randomUUID(),
                    "BMW",
                    "B-CS1177E",
                    "available",
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

        Car car1 = new Car();
        car1.setBrand("Volkswagen");
        car1.setLicensePlate("V-CS1122E");
        car1.setManufacturer("Hamburger Manufacturer");
        car1.setOperationCity("Hamburg");
        car1.setStatus("available");
        car1.setCreatedAt(LocalDateTime.now());
        car1.setLastUpdatedAt(LocalDateTime.now());

        Car car2 = new Car();
        car2.setBrand("Volkswagen");
        car2.setLicensePlate("V-CS3344E");
        car2.setManufacturer("Hamburger Manufacturer");
        car2.setOperationCity("Hamburg");
        car2.setStatus("available");
        car2.setCreatedAt(LocalDateTime.now());
        car2.setLastUpdatedAt(LocalDateTime.now());

        List<Car> carList = List.of(car1, car2);

        String inputJson = super.mapToJson(carList);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson))
            .andExpect(status().isCreated())
            .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

}
