package com.angmas.mutation.service;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.angmas.mutation.domain.Driver;
import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.domain.Vehicle;

public class XmlToPolicyMappingService {

	private AcordEventProcessorHelper helper;
	private XMLEventReader xmlEventReader;
	private boolean isInsuredOrPrincipalRole;
	private boolean inPersPolicyNode;
	private String customerNameHold;
	private String lobCdHold;
	private String amtHold;
	private List<Policy> policies;
	private Policy policy;
	private Vehicle vehicle;
	private Driver driver;
	
	public XmlToPolicyMappingService() {
		AcordEventProcessorHelper helper = new AcordEventProcessorHelper();
	}

	public List<Policy> mapPolicies(String xmlString) throws XMLStreamException {
		this.xmlEventReader = getEventReaderInstance(xmlString);
		processXmlElements();
		return policies;
	}

	private void processXmlElements() throws XMLStreamException {
		while (xmlEventReader.hasNext()) {
			AcordEventProcessorHelper helper = new AcordEventProcessorHelper();
			helper.event = xmlEventReader.nextEvent();
			switch (helper.event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					doStartElementProcessing(helper.event);
					break;
				case XMLStreamConstants.END_ELEMENT:
					doEndElementProcessing(helper.event);
					break;
			}

		}
	}
	

	private void doStartElementProcessing(XMLEvent event) throws XMLStreamException {
		StartElement startElement = event.asStartElement();
		
		switch (startElement.getName().getLocalPart()) {
		case "PersAutoPolicyQuoteInqRq":
			doPersAutoPolicyQuoteInsRqStartProcessing(event);
			break;
		case "PersPolicy":
			doPersPolicyStartProcessing(event);
			break;
		case "PolicyNumber":
			doPolicyNumberStartProcessing(event);
			break;
		case "CommercialName":
			doCommercialNameStartProcessing(event);
			break;
		case "InsuredOrPrincipalRoleCd":
			doInsuredOrPrincipalRoleCdStartProcessing(event);
			break;
		case "LOBCd":
			doLOBCdStartProcessing(event);
			break;
		case "Amt":
			doAmtStartProcessing(event);
			break;
		case "PersVeh":
			doPersVehStartProcessing(event, startElement);
			break;
		case "PersDriver":
			doPersDriverStartProcessing(event, startElement);
			break;
		case "Manufacturer":
			doManufacturerStartProcessing(event);
			break;
		case "Model":
			doModelStartProcessing(event);
			break;
		case "ModelYear":
			doModelYearStartProcessing(event);
			break;
		case "BirthDt":
			doBirthDtStartProcessing(event);
			break;
		}
	
	}

	private void doEndElementProcessing(XMLEvent event) {
		EndElement endElement = event.asEndElement();
		switch (endElement.getName().getLocalPart()) {
		case "PersAutoPolicyQuoteInqRq":
			doPersAutoPolicyQuoteInqRqEndProcesing();
			break;
		case "InsuredOrPrincipal":
			doInsuredOrPrincipalEndProcessing();
			break;
		case "PersPolicy":
			doPersPolicyEndProcessing();
			break;
		case "CurrentTermAmt":
			doCurrentTermAmtEndProcessing();
			break;
		case "PersVeh":
			doPersVehEndProcessing();
			break;
		case "PersDriver":
			doPersDriverEndProcessing();
			break;
		}
	}

	private void doPersAutoPolicyQuoteInsRqStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		policy = new Policy();
	}

	private void doPersAutoPolicyQuoteInqRqEndProcesing() {
		policies.add(policy);
	}

	private void doPersVehStartProcessing(XMLEvent event, StartElement startElement) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		vehicle = new Vehicle();
		Attribute id = startElement.getAttributeByName(new QName("id"));
		vehicle.setId(id.getValue());
	}
	
	private void doPersVehEndProcessing() {
		policy.getVehicles().add(vehicle);
	}

	private void doPersDriverStartProcessing(XMLEvent event, StartElement startElement) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		driver = new Driver();
		Attribute id = startElement.getAttributeByName(new QName("id"));
		driver.setId(id.getValue());		
	}

	private void doPersDriverEndProcessing() {
		driver.setDriverName(customerNameHold);
		customerNameHold = null;
		policy.getDrivers().add(driver);
	}

	private void doPersPolicyStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		inPersPolicyNode = true;
	}

	private void doPersPolicyEndProcessing() {
		policy.setPolicyType(lobCdHold);
		lobCdHold = "";
		inPersPolicyNode = false;
	}

	private void doInsuredOrPrincipalRoleCdStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		String insuredOrPrincipalRoleCd = event.asCharacters().getData();
		isInsuredOrPrincipalRole = insuredOrPrincipalRoleCd.equalsIgnoreCase("Insured");
	}

	private void doInsuredOrPrincipalEndProcessing() {
		if (isInsuredOrPrincipalRole) {
			policy.setCustomerName(customerNameHold);
		}
		
		customerNameHold = null;
		
		isInsuredOrPrincipalRole = false;
	}

	private void doAmtStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		amtHold = event.asCharacters().getData();
	}

	private void doLOBCdStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		lobCdHold = event.asCharacters().getData();
	}

	private void doCommercialNameStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		customerNameHold = event.asCharacters().getData();
	}

	private void doPolicyNumberStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		policy.setPolicyNumber(event.asCharacters().getData());
	}

	private void doCurrentTermAmtEndProcessing() {
		if (inPersPolicyNode) {
			policy.setTotalPremium(amtHold);
		}
	}

	private void doManufacturerStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		vehicle.setMake(event.asCharacters().getData());
	}

	private void doModelStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		vehicle.setModel(event.asCharacters().getData());		
	}

	private void doModelYearStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		vehicle.setModelYear(event.asCharacters().getData());
	}

	private void doBirthDtStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		driver.setBirthDate(event.asCharacters().getData());		
	}

	private XMLEventReader getEventReaderInstance(String xml) throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		return factory.createXMLEventReader(stream);
	}

}
