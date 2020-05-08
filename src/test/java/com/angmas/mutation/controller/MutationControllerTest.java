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

}
