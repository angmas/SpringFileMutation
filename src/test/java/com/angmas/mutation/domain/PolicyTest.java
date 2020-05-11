package com.angmas.mutation.domain;

import com.angmas.mutation.common.CurrencyFormatter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PolicyTest {
    private Policy policy;
    private ObjectMapper mapper;

    @BeforeEach
    void init() {
        policy = new Policy();
        mapper = new ObjectMapper();
    }

    @Test
    void jsonFieldsAreNullWhenPolicyHasNullFields() throws JsonProcessingException {
        String jsonString = "{\"policyNumber\":null,\"customerName\":null,\"policyType\":null,\"totalPremium\":null,\"vehicles\":[],\"drivers\":[]}";
        assertEquals(jsonString, mapper.writeValueAsString(policy));
    }

    @Test
    void jsonTotalPremiumScaleIs2() throws JsonProcessingException {
        policy.setTotalPremium(CurrencyFormatter.formatStringToNumber("100"));
        String jsonString = "{\"policyNumber\":null,\"customerName\":null,\"policyType\":null,\"totalPremium\":100.00,\"vehicles\":[],\"drivers\":[]}";
        assertEquals(jsonString, mapper.writeValueAsString(policy));
    }

    @Test
    void jsonFieldsReturnPolicyStringData() throws JsonProcessingException {
        policy.setPolicyNumber("1234a");
        policy.setCustomerName("Tiny Tim");
        policy.setPolicyType("some type");
        String jsonString = "{\"policyNumber\":\"1234a\",\"customerName\":\"Tiny Tim\",\"policyType\":\"some type\",\"totalPremium\":null,\"vehicles\":[],\"drivers\":[]}";
        assertEquals(jsonString, mapper.writeValueAsString(policy));
    }

}