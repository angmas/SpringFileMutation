package com.angmas.mutation.service;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;

import com.angmas.mutation.domain.Driver;
import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.domain.Vehicle;

public class XmlToPolicyMappingService {

	private AcordEventProcessorHelper helper;
	
	public XmlToPolicyMappingService() {
		this.helper = new AcordEventProcessorHelper();
	}

	public List<Policy> mapPolicies(String xmlString) throws XMLStreamException {
		helper.xmlEventReader = getEventReaderInstance(xmlString);
		processXmlElements();
		return helper.policies;
	}

	private void processXmlElements() throws XMLStreamException {
		while (helper.xmlEventReader.hasNext()) {
			helper.setNextEvent();
			switch (helper.event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					doStartElementProcessing();
					break;
				case XMLStreamConstants.END_ELEMENT:
					doEndElementProcessing();
					break;
			}

		}
	}
	

	private void doStartElementProcessing() throws XMLStreamException {
		AcordStartEventProcessorFactory factory = new AcordStartEventProcessorFactory();
		helper.setStartElement();
		
		AcordElementStartEventProcessor elementProcessor = factory.getProcessor(helper.getStartElementName());
		
		if (elementProcessor != null) {
			elementProcessor.doStartProcessing(helper);
		}
		
//		switch (helper.getStartElementName()) {
//		case "PersAutoPolicyQuoteInqRq":
//			doPersAutoPolicyQuoteInsRqStartProcessing(helper);
//			break;
//		case "PersPolicy":
//			doPersPolicyStartProcessing(helper);
//			break;
//		case "PolicyNumber":
//			doPolicyNumberStartProcessing(helper);
//			break;
//		case "CommercialName":
//			doCommercialNameStartProcessing(helper);
//			break;
//		case "InsuredOrPrincipalRoleCd":
//			doInsuredOrPrincipalRoleCdStartProcessing(helper);
//			break;
//		case "LOBCd":
//			doLOBCdStartProcessing(helper);
//			break;
//		case "Amt":
//			doAmtStartProcessing(helper);
//			break;
//		case "PersVeh":
//			doPersVehStartProcessing(helper);
//			break;
//		case "PersDriver":
//			doPersDriverStartProcessing(helper);
//			break;
//		case "Manufacturer":
//			doManufacturerStartProcessing(helper);
//			break;
//		case "Model":
//			doModelStartProcessing(helper);
//			break;
//		case "ModelYear":
//			doModelYearStartProcessing(helper);
//			break;
//		case "BirthDt":
//			doBirthDtStartProcessing(helper);
//			break;
//		}
//	
	}

	private void doEndElementProcessing() {
		helper.setEndElement();
		switch (helper.getEndElementName()) {
		case "PersAutoPolicyQuoteInqRq":
			doPersAutoPolicyQuoteInqRqEndProcesing(helper);
			break;
		case "InsuredOrPrincipal":
			doInsuredOrPrincipalEndProcessing(helper);
			break;
		case "PersPolicy":
			doPersPolicyEndProcessing(helper);
			break;
		case "CurrentTermAmt":
			doCurrentTermAmtEndProcessing(helper);
			break;
		case "PersVeh":
			doPersVehEndProcessing(helper);
			break;
		case "PersDriver":
			doPersDriverEndProcessing(helper);
			break;
		}
	}

	private void doPersAutoPolicyQuoteInsRqStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.policy = new Policy();
	}

	private void doPersAutoPolicyQuoteInqRqEndProcesing(AcordEventProcessorHelper helper) {
		helper.policies.add(helper.policy);
	}

	private void doPersVehStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.vehicle = new Vehicle();
		Attribute id = helper.startElement.getAttributeByName(new QName("id"));
		helper.vehicle.setId(id.getValue());
	}
	
	private void doPersVehEndProcessing(AcordEventProcessorHelper helper) {
		helper.policy.getVehicles().add(helper.vehicle);
	}

	private void doPersDriverStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.driver = new Driver();
		Attribute id = helper.startElement.getAttributeByName(new QName("id"));
		helper.driver.setId(id.getValue());		
	}

	private void doPersDriverEndProcessing(AcordEventProcessorHelper helper) {
		helper.driver.setDriverName(helper.customerNameHold);
		helper.customerNameHold = null;
		helper.policy.getDrivers().add(helper.driver);
	}

	private void doPersPolicyStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.inPersPolicyNode = true;
	}

	private void doPersPolicyEndProcessing(AcordEventProcessorHelper helper) {
		helper.policy.setPolicyType(helper.lobCdHold);
		helper.lobCdHold = "";
		helper.inPersPolicyNode = false;
	}

	private void doInsuredOrPrincipalRoleCdStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		String insuredOrPrincipalRoleCd = helper.getElementData();
		helper.isInsuredOrPrincipalRole = insuredOrPrincipalRoleCd.equalsIgnoreCase("Insured");
	}

	private void doInsuredOrPrincipalEndProcessing(AcordEventProcessorHelper helper) {
		if (helper.isInsuredOrPrincipalRole) {
			helper.policy.setCustomerName(helper.customerNameHold);
		}
		
		helper.customerNameHold = null;
		
		helper.isInsuredOrPrincipalRole = false;
	}

	private void doAmtStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.amtHold = helper.getElementData();
	}

	private void doLOBCdStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.lobCdHold = helper.getElementData();
	}

	private void doCommercialNameStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.customerNameHold = helper.getElementData();
	}

	private void doPolicyNumberStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.policy.setPolicyNumber(helper.getElementData());
	}

	private void doCurrentTermAmtEndProcessing(AcordEventProcessorHelper helper) {
		if (helper.inPersPolicyNode) {
			helper.policy.setTotalPremium(helper.amtHold);
		}
	}

	private void doManufacturerStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.vehicle.setMake(helper.getElementData());
	}

	private void doModelStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.vehicle.setModel(helper.getElementData());		
	}

	private void doModelYearStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.vehicle.setModelYear(helper.getElementData());
	}

	private void doBirthDtStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.driver.setBirthDate(helper.getElementData());		
	}

	private XMLEventReader getEventReaderInstance(String xml) throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		return factory.createXMLEventReader(stream);
	}

}
