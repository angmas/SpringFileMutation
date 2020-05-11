package com.angmas.mutation.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Vehicle vehicle;
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        vehicle = new Vehicle();
        mapper = new ObjectMapper();
    }

    @Test
    void jsonFieldsReturnNull() throws JsonProcessingException {
        String jsonString = "{\"id\":null,\"make\":null,\"model\":null,\"modelYear\":null}";
        assertEquals(jsonString, mapper.writeValueAsString(vehicle));
    }

    @Test
    void jsonFieldsReturnValidData() throws JsonProcessingException {
        vehicle.setId("vv");
        vehicle.setMake("Honda");
        vehicle.setModel("Odyssey");
        vehicle.setModel("2013");
        String jsonString = "{\"id\":\"vv\",\"make\":\"Honda\",\"model\":\"2013\",\"modelYear\":null}";
        assertEquals(jsonString, mapper.writeValueAsString(vehicle));
    }
}