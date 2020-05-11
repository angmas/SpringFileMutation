package com.angmas.mutation.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    private Driver driver;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        driver = new Driver();
        mapper = new ObjectMapper();
    }

    @Test
    void jsonFieldsReturnNull() throws JsonProcessingException {
        String jsonString = "{\"id\":null,\"driverName\":null,\"age\":null}";
        assertEquals(jsonString, mapper.writeValueAsString(driver));
    }

    @Test
    void jsonFieldsReturnValidDriverStringData() throws JsonProcessingException {
        driver.setId("xx");
        driver.setDriverName("The Man");
        driver.setBirthDate(new LocalDate("2005-01-01"));
        String jsonString = "{\"id\":\"xx\",\"driverName\":\"The Man\",\"age\":15}";
        assertEquals(jsonString, mapper.writeValueAsString(driver));
    }
}