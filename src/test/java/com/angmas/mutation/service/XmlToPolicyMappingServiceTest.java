package com.angmas.mutation.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.angmas.mutation.domain.Policy;

class XmlToPolicyMappingServiceTest {
	
	private static String xmlString;

	@BeforeAll
	static void setup() throws IOException {
		xmlString = new String(Files.readAllBytes(Paths.get("./data.xml")));
//		Path currentDir = Paths.get("data.xml");
//		System.out.println(currentDir.toAbsolutePath()); 
//		System.out.println(xmlString); 
		
	}
	
	@BeforeEach
	void init() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void returnsListOfPolicies() throws XMLStreamException {
		XmlToPolicyMappingService xml2pol = new XmlToPolicyMappingService();
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertEquals(3, policies.size());
	}
	
	

}
