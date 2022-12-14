package com.wagner.fleetmanager.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest(controllers = CarController.class)
@ExtendWith(SpringExtension.class)
class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private CarController controller;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getAllCarsTest() throws Exception {
        mockMvc.perform(get("/v1/cars"))
            .andExpect(status().is2xxSuccessful());
    }

}
