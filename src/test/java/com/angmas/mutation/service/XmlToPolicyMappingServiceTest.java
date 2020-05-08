package com.angmas.mutation.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.stream.XMLStreamException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.angmas.mutation.domain.Driver;
import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.domain.Vehicle;

class XmlToPolicyMappingServiceTest {
	
	Logger logger = LoggerFactory.getLogger(XmlToPolicyMappingServiceTest.class);
	
	private static String xmlString;
	private XmlToPolicyMappingService xml2policyObject;

	@BeforeAll
	static void setup() throws IOException {
		xmlString = new String(Files.readAllBytes(Paths.get("./data.xml")));
//		Path currentDir = Paths.get("data.xml");
//		System.out.println(currentDir.toAbsolutePath()); 
//		System.out.println(xmlString); 
		
	}
	
	@BeforeEach
	void init() throws Exception {
		xml2policyObject = new XmlToPolicyMappingService();
	}
	

	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void returnsListOfPolicies() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		assertEquals(3, policies.size());
	}
	
	@Test
	void mapsPolicyNumbers() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		assertAll("policy numbers",
			() -> assertEquals( "4843607801", policies.get(0).getPolicyNumber()),
			() -> assertEquals("XT678", policies.get(1).getPolicyNumber()),
			() -> assertEquals("YY567", policies.get(2).getPolicyNumber())
		);
	}
	
	@Test
	void mapsCustomerNames() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		assertAll("customer names",
			() -> assertEquals( "Tyrion Spiegelman", policies.get(0).getCustomerName()),
			() -> assertEquals("Fred Flinstone", policies.get(1).getCustomerName()),
			() -> assertEquals("Mickey Mouse", policies.get(2).getCustomerName())
		);
	}
	
	@Test
	void mapsPolicyType() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		assertAll("policy types",
				() -> assertEquals( "AUTOPA", policies.get(0).getPolicyType()),
				() -> assertEquals("AUTOPB", policies.get(1).getPolicyType()),
				() -> assertEquals("AUTOPC", policies.get(2).getPolicyType())
			);
	}
	
	@Test
	void mapsTotalPremium() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		assertAll("total premiums",
				() -> assertEquals( new BigDecimal("2135.19"), policies.get(0).getTotalPremium()),
				() -> assertEquals(new BigDecimal("300.00"), policies.get(1).getTotalPremium()),
				() -> assertEquals(new BigDecimal("3000.25"), policies.get(2).getTotalPremium())
			);
	}
	
	@Test
	void createsVehicleObjects() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		assertAll("vehicle counts",
				() -> assertEquals( 5, policies.get(0).getVehicles().size()),
				() -> assertEquals( 1, policies.get(1).getVehicles().size()),
				() -> assertEquals( 2, policies.get(2).getVehicles().size())
		);
	}
	
	@Test
	void mapVehicleIds() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		List<Vehicle> vehicles = policies.get(0).getVehicles();
		assertAll("vehicle ids",
				() -> assertEquals( "V1", vehicles.get(0).getId()),
				() -> assertEquals( "V2", vehicles.get(1).getId()),
				() -> assertEquals( "V3", vehicles.get(2).getId()),
				() -> assertEquals( "V4", vehicles.get(3).getId()),
				() -> assertEquals( "V5", vehicles.get(4).getId())
		);
	}
	
	@Test
	void mapVehicleMake() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		List<Vehicle> vehicles = policies.get(0).getVehicles();
		assertAll("vehicle make",
				() -> assertEquals( "TOYT", vehicles.get(0).getMake()),
				() -> assertEquals( "PASONS", vehicles.get(1).getMake()),
				() -> assertEquals( "TOYT", vehicles.get(2).getMake()),
				() -> assertEquals( "VICTORY", vehicles.get(3).getMake()),
				() -> assertEquals( "YAMAHA", vehicles.get(4).getMake())
		);
	}
	
	@Test
	void mapVehicleModel() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		List<Vehicle> vehicles = policies.get(0).getVehicles();
		assertAll("vehicle models",
				() -> assertEquals( "AVALON XL/XLS/TR/LTD", vehicles.get(0).getModel()),
				() -> assertEquals( "FABRICATION", vehicles.get(1).getModel()),
				() -> assertEquals( "TACOMA DOUBLECAB", vehicles.get(2).getModel()),
				() -> assertEquals( "VEGAS", vehicles.get(3).getModel()),
				() -> assertEquals( "XV365DB", vehicles.get(4).getModel())
		);
	}
	
	@Test
	void mapVehicleModelYear() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		List<Vehicle> vehicles = policies.get(0).getVehicles();
		assertAll("vehicle models",
				() -> assertEquals( "2006", vehicles.get(0).getModelYear()),
				() -> assertEquals( "2009", vehicles.get(1).getModelYear()),
				() -> assertEquals( "2010", vehicles.get(2).getModelYear()),
				() -> assertEquals( "2010", vehicles.get(3).getModelYear()),
				() -> assertEquals( "2013", vehicles.get(4).getModelYear())
		);
	}
	
	@Test
	void createsDriverObjects() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		assertAll("driver counts",
				() -> assertEquals( 1, policies.get(0).getDrivers().size()),
				() -> assertEquals( 2, policies.get(1).getDrivers().size()),
				() -> assertEquals( 3, policies.get(2).getDrivers().size())
		);
	}
	
	@Test
	void mapDriverId() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		List<Driver> drivers = policies.get(2).getDrivers();
		assertAll("driver id",
				() -> assertEquals( "D1", drivers.get(0).getId()),
				() -> assertEquals( "D2", drivers.get(1).getId()),
				() -> assertEquals( "D1", drivers.get(2).getId())
		);
	}
	
	@Test
	void mapDriverName() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		List<Driver> drivers = policies.get(2).getDrivers();
		assertAll("driver name",
				() -> assertEquals( "Mickey Mouse", drivers.get(0).getDriverName()),
				() -> assertEquals( null, drivers.get(1).getDriverName()),
				() -> assertEquals( "Goofy", drivers.get(2).getDriverName())
		);
	}
	
	@Test
	void mapDriverBirthDate() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		List<Driver> drivers = policies.get(2).getDrivers();
		assertAll("driver birth date",
				() -> assertEquals( "1950-03-01", drivers.get(0).getBirthDate().toString("yyyy-MM-dd")),
				() -> assertEquals( "1956-03-30", drivers.get(1).getBirthDate().toString("yyyy-MM-dd")),
				() -> assertEquals( "1959-03-01", drivers.get(2).getBirthDate().toString("yyyy-MM-dd"))
		);
	}
	
	@Test
	void getDriversAge() throws XMLStreamException {
		List<Policy> policies = xml2policyObject.mapPolicies(xmlString);
		List<Driver> drivers = policies.get(2).getDrivers();
		assertAll("driver birth date",
				() -> assertEquals( 70, drivers.get(0).getAge()),
				() -> assertEquals( 64, drivers.get(1).getAge()),
				() -> assertEquals( 61, drivers.get(2).getAge())
		);
	}
}
