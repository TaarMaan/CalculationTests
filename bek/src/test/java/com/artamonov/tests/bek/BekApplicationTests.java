package com.artamonov.tests.bek;

import com.artamonov.tests.bek.model.Numbers;
import com.artamonov.tests.bek.model.NumbersDTO;
import com.artamonov.tests.bek.model.entity.Calculations;
import com.artamonov.tests.bek.repository.CalculationsRepository;
import com.artamonov.tests.bek.service.CalculationsService;
import com.artamonov.tests.bek.service.OperationsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@ContextConfiguration(initializers = {BekApplicationTests.Initializer.class})
@AutoConfigureMockMvc
@Testcontainers
public class BekApplicationTests {
    @Container
    public static PostgreSQLContainer<?> postgresSQLContainer = new PostgreSQLContainer<>("postgres:15.1")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("root")
            .withInitScript("database/V1__create.sql");
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    CalculationsRepository calculationsRepository;
    @Autowired
    CalculationsService calculationsService;

    @Test
    @Transactional
    public void calculatorByParameters() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-01-29");
        Date endDate = df.parse("2023-06-24");
        List<Calculations> calculations = calculationsRepository.
                findByParameters(2, 2, "ADDITION", startDate, endDate);
        assertEquals(1, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorWithoutOperation() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-01-29");
        Date endDate = df.parse("2023-06-24");
        List<Calculations> calculations = calculationsRepository.
                findByParameters(2, 2, "",startDate, endDate);
        assertEquals(3, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorFindByDates() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-01-29");
        Date endDate = df.parse("2023-06-24");
        List<Calculations> calculations
                = calculationsRepository.findByDate(startDate, endDate);
        assertEquals(4, calculations.size());
    }

    @Test
    @Transactional
    public void calculatorFindByDatesAndDivisionOperation() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-01-29");
        Date endDate = df.parse("2023-06-24");
        List<Calculations> calculations = calculationsRepository.findByParameters(2, 2, "DIVISION", startDate, endDate);
        assertEquals(1, calculations.size());
    }

    @Test
    @Transactional
    public void createPlus() {
        Numbers numbers = new Numbers();
        numbers.setNum1(0b101);
        numbers.setNum2(0b111);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '+');
        assertEquals(12, res);
    }

    @Test
    @Transactional
    public void createDivision() {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x150);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '/');
        assertEquals(112, res);
    }

    @Test
    @Transactional
    public void createMult() {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '*');
        assertEquals(1014, res);
    }

    @Test
    @Transactional
    public void createSubtract() {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '-');
        assertEquals(335, res);
    }

    @Test
    public void testSub() throws Exception {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '-');
        var response = this.mockMvc.perform(get("/calculations/subtraction")
                        .param("num1", String.valueOf(numbers.getNum1()))
                        .param("num2", String.valueOf(numbers.getNum2()))
                        .param("sys", String.valueOf(numbers.getSystem())))
                .andReturn().getResponse();
        assertEquals(String.valueOf(res), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testAdd() throws Exception {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '+');
        var response = this.mockMvc.perform(get("/calculations/addition")
                        .param("num1", String.valueOf(numbers.getNum1()))
                        .param("num2", String.valueOf(numbers.getNum2()))
                        .param("system", String.valueOf(numbers.getSystem())))
                .andReturn().getResponse();
        assertEquals(String.valueOf(res), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testMult() throws Exception {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '*');
        var response = this.mockMvc.perform(get("/calculations/multiplication")
                        .param("num1", String.valueOf(numbers.getNum1()))
                        .param("num2", String.valueOf(numbers.getNum2()))
                        .param("system", String.valueOf(numbers.getSystem())))
                .andReturn().getResponse();
        assertEquals(String.valueOf(res), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testDiv() throws Exception {
        Numbers numbers = new Numbers();
        numbers.setNum1(0x152);
        numbers.setNum2(0b11);
        numbers.setSystem(2);
        var res = OperationsService.calculate(numbers, '/');
        var response = this.mockMvc.perform(get("/calculations/division")
                        .param("num1", String.valueOf(numbers.getNum1()))
                        .param("num2", String.valueOf(numbers.getNum2()))
                        .param("system", String.valueOf(numbers.getSystem())))
                .andReturn().getResponse();
        assertEquals(String.valueOf(res), response.getContentAsString());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void findByParameters() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = df.parse("2023-01-29");
        Date endDate = df.parse("2023-06-24");
        NumbersDTO numbersDTO = new NumbersDTO();
        numbersDTO.setOperationName("ADDITION");
        numbersDTO.setStartDate(startDate);
        numbersDTO.setEndDate(endDate);
        var response = this.mockMvc.perform(get("/calculations/findByDate?numberSystemOne=2&numberSystemTwo=2&endDate=2023-06-24&startDate=2023-01-29")
                .param("operationName", numbersDTO.getOperationName())).andReturn().getResponse();
        List<Calculations> calculations = calculationsRepository.findByParameters(2, 2, numbersDTO.getOperationName(), startDate, endDate);
        assertEquals(calculations.size(), response.getHeaders("Content-Type").size());
        assertEquals(200, response.getStatus());
    }

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgresSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgresSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgresSQLContainer.getPassword()

            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}