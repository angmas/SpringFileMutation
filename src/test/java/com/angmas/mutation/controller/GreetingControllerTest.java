package com.angmas.mutation.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GreetingControllerTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void returnsIgotIt() {
		GreetingController gc = new GreetingController();
		String requestBody = "<Policy><policyNumber>12345</policyNumber></Policy>";
		String expectedResult = "I got it";
		String response = gc.getPolicy(requestBody);
		assertEquals(response, expectedResult);
	}

}
