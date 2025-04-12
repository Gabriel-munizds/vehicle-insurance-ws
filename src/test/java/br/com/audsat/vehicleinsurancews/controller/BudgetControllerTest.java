package br.com.audsat.vehicleinsurancews.controller;

import br.com.audsat.vehicleinsurancews.useful.Utility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class BudgetControllerTest {
    @Autowired
    private Utility utility;

    @Test
    void createBudget() {
    }

    @Test
    void findBudgetByInsuranceId() {
    }

    @Test
    void updateBudget() {
    }

    @Test
    void deleteBudgetByInsuranceId() {
    }
}