package com.angmas.mutation.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import com.angmas.mutation.domain.Policy;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

class MutationControllerTest {

	private static String xmlString;
	private static String jsonString;
	
	@BeforeAll
	static void setup() throws IOException {
		xmlString = new String(Files.readAllBytes(Paths.get("./data.xml")));
		jsonString = new String(Files.readAllBytes(Paths.get("./data.json")));
		
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void returnsJSON() throws JsonProcessingException {
		MutationController mc = new MutationController();
		List<Policy> policies = mc.getPolicy(xmlString);
		ObjectWriter ow = new ObjectMapper().writer();
		assertEquals(jsonString, ow.writeValueAsString(policies));
	}
	
	@Test
	void exceptionIsThrown() {
		MutationController mc = new MutationController();
		Exception exception = assertThrows(ResponseStatusException.class, () -> {
			mc.getPolicy("<ACORD");
		});
		
		String expectedMessage = "Cannot process this XML";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void performance() throws JsonProcessingException {
		MutationController mc = new MutationController();
		
		for(int i=0; i < 100; i++) {
			long startTime = System.nanoTime();
			
			List<Policy> policies = mc.getPolicy(xmlString);
			
			long endTime = System.nanoTime();
			
	        System.out.println("Time difference ::: "+(endTime-startTime)+" nano seconds");
		}
	}

}
