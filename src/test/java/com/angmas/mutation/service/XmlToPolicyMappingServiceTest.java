package com.angmas.mutation.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
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

import com.angmas.mutation.domain.Driver;
import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.domain.Vehicle;

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
		assertAll("customer names",
			() -> assertEquals( "Tyrion Spiegelman", policies.get(0).getCustomerName()),
			() -> assertEquals("Fred Flinstone", policies.get(1).getCustomerName()),
			() -> assertEquals("Mickey Mouse", policies.get(2).getCustomerName())
		);
	}
	
	@Test
	void mapsPolicyType() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertAll("policy types",
				() -> assertEquals( "AUTOPA", policies.get(0).getPolicyType()),
				() -> assertEquals("AUTOPB", policies.get(1).getPolicyType()),
				() -> assertEquals("AUTOPC", policies.get(2).getPolicyType())
			);
	}
	
	@Test
	void mapsTotalPremium() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertAll("total premiums",
				() -> assertEquals( new BigDecimal("2135.19"), policies.get(0).getTotalPremium()),
				() -> assertEquals(new BigDecimal("300.00"), policies.get(1).getTotalPremium()),
				() -> assertEquals(new BigDecimal("3000.25"), policies.get(2).getTotalPremium())
			);
	}
	
	@Test
	void createsVehicleObjects() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertAll("vehicle counts",
				() -> assertEquals( 5, policies.get(0).getVehicles().size()),
				() -> assertEquals( 1, policies.get(1).getVehicles().size()),
				() -> assertEquals( 2, policies.get(2).getVehicles().size())
		);
	}
	
	@Test
	void mapVehicleIds() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		List<Vehicle> vehicles = policies.get(0).getVehicles();
		assertAll("vehicle ids",
				() -> assertEquals( "V1", policies.get(0).getVehicles().get(0).getId()),
				() -> assertEquals( "V2", policies.get(0).getVehicles().get(1).getId()),
				() -> assertEquals( "V3", policies.get(0).getVehicles().get(2).getId()),
				() -> assertEquals( "V4", policies.get(0).getVehicles().get(3).getId()),
				() -> assertEquals( "V5", policies.get(0).getVehicles().get(4).getId())
		);
	}
	
	@Test
	void mapVehicleMake() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		List<Vehicle> vehicles = policies.get(0).getVehicles();
		assertAll("vehicle make",
				() -> assertEquals( "TOYT", policies.get(0).getVehicles().get(0).getMake()),
				() -> assertEquals( "PASONS", policies.get(0).getVehicles().get(1).getMake()),
				() -> assertEquals( "TOYT", policies.get(0).getVehicles().get(2).getMake()),
				() -> assertEquals( "VICTORY", policies.get(0).getVehicles().get(3).getMake()),
				() -> assertEquals( "YAMAHA", policies.get(0).getVehicles().get(4).getMake())
		);
	}
	
	@Test
	void mapVehicleModel() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		List<Vehicle> vehicles = policies.get(0).getVehicles();
		assertAll("vehicle models",
				() -> assertEquals( "AVALON XL/XLS/TR/LTD", policies.get(0).getVehicles().get(0).getModel()),
				() -> assertEquals( "FABRICATION", policies.get(0).getVehicles().get(1).getModel()),
				() -> assertEquals( "TACOMA DOUBLECAB", policies.get(0).getVehicles().get(2).getModel()),
				() -> assertEquals( "VEGAS", policies.get(0).getVehicles().get(3).getModel()),
				() -> assertEquals( "XV365DB", policies.get(0).getVehicles().get(4).getModel())
		);
	}
	
	@Test
	void createsDriverObjects() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		assertAll("driver counts",
				() -> assertEquals( 1, policies.get(0).getDrivers().size()),
				() -> assertEquals( 2, policies.get(1).getDrivers().size()),
				() -> assertEquals( 3, policies.get(2).getDrivers().size())
		);
	}
	
	@Test
	void mapDriverId() throws XMLStreamException {
		List<Policy> policies = xml2pol.mapPolicies(xmlString);
		List<Driver> drivers = policies.get(2).getDrivers();
		assertAll("driver id",
				() -> assertEquals( "D1", drivers.get(0).getId()),
				() -> assertEquals( "D2", drivers.get(1).getId()),
				() -> assertEquals( "D1", drivers.get(2).getId())
		);
	}
}
