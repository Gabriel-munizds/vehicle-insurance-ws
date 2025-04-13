package br.com.audsat.vehicleinsurancews.controller;

import br.com.audsat.vehicleinsurancews.config.mapper.GenericMapper;
import br.com.audsat.vehicleinsurancews.dto.BudgetDtoOut;
import br.com.audsat.vehicleinsurancews.useful.GenericRepository;
import br.com.audsat.vehicleinsurancews.useful.PojoFactory;
import br.com.audsat.vehicleinsurancews.useful.Queries;
import br.com.audsat.vehicleinsurancews.useful.Utility;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class BudgetControllerTest {
    @Autowired
    private Utility utility;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GenericMapper genericMapper;
    @Autowired
    private GenericRepository genericRepository;

    @Test
    void createBudget_ShouldReturnStatusCode201() throws Exception {
        String content = utility.structureCreatedPostStatus201("/budget", PojoFactory.createValidBudgetDtoIn(), this.mockMvc);
        Object result = new ObjectMapper().readValue(content, Object.class);
        BudgetDtoOut dto = genericMapper.toObject(result, BudgetDtoOut.class);
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(content);
        Assertions.assertFalse(content.isEmpty());
    }

    @Test
    void findBudgetByInsuranceId() throws Exception {
        genericRepository.executeQuery(Queries.init_insurances);
        String content = utility.structureOkGetStatus200("/budget/5000", this.mockMvc);
        Object result = new ObjectMapper().readValue(content, Object.class);
        BudgetDtoOut dto = genericMapper.toObject(result, BudgetDtoOut.class);
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(content);
        Assertions.assertFalse(content.isEmpty());
    }

    @Test
    void updateBudget() throws Exception {
        genericRepository.executeQuery(Queries.init_insurances_2);
        String content = utility.structureOkPutStatus200("/budget/5001", PojoFactory.createValidBudgetDtoIn(), this.mockMvc);
        Object result = new ObjectMapper().readValue(content, Object.class);
        BudgetDtoOut dto = genericMapper.toObject(result, BudgetDtoOut.class);
        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(content);
        Assertions.assertFalse(content.isEmpty());
    }

    @Test
    void deleteBudgetByInsuranceId() throws Exception {
        genericRepository.executeQuery(Queries.init_insurances_3);
        boolean content = utility.structureDeleteStatus204("/budget/5002", this.mockMvc);
        Assertions.assertFalse(content);
    }
}