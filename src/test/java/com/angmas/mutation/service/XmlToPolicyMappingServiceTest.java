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
import org.junit.jupiter.api.Test;

import com.angmas.mutation.domain.Policy;

class XmlToPolicyMappingServiceTest {
	
	private static String xmlString;
	private XmlToPolicyMappingService xml2pol;

	@BeforeAll
	static void setup() throws IOException {
		xmlString = new String(Files.readAllBytes(Paths.get("./data.xml")));
//		Path currentDir = Paths.get("data.xml");
//		System.out.println(currentDir.toAbsolutePath()); 
//		System.out.println(xmlString); 
		
	}
	
	@BeforeEach
	void init() throws Exception {
		xml2pol = new XmlToPolicyMappingService();
	}
	

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void returnsListOfPolicies() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertEquals(3, policies.size());
	}
	
	@Test
	void mapsPolicyNumbers() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertAll("policy numbers",
			() -> assertEquals( "4843607801", policies.get(0).getPolicyNumber()),
			() -> assertEquals("XT678", policies.get(1).getPolicyNumber()),
			() -> assertEquals("YY567", policies.get(2).getPolicyNumber())
		);
	}
	
	@Test
	void mapsCustomerNames() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertAll("policy numbers",
			() -> assertEquals( "Tyrion Spiegelman", policies.get(0).getCustomerName()),
			() -> assertEquals("Fred Flinstone", policies.get(1).getCustomerName()),
			() -> assertEquals("Mickey Mouse", policies.get(2).getCustomerName())
		);
	}
	
	@Test
	void mapsPolicyType() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertAll("policy numbers",
				() -> assertEquals( "AUTOPA", policies.get(0).getPolicyType()),
				() -> assertEquals("AUTOPB", policies.get(1).getPolicyType()),
				() -> assertEquals("AUTOPC", policies.get(2).getPolicyType())
			);
	}
}
