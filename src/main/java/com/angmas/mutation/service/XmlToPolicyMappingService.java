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

	private void doPersAutoPolicyQuoteInqRqEndProcesing(AcordEventProcessorHelper helper) {
		helper.policies.add(helper.policy);
	}

	private void doPersVehEndProcessing(AcordEventProcessorHelper helper) {
		helper.policy.getVehicles().add(helper.vehicle);
	}

	private void doPersDriverEndProcessing(AcordEventProcessorHelper helper) {
		helper.driver.setDriverName(helper.customerNameHold);
		helper.customerNameHold = null;
		helper.policy.getDrivers().add(helper.driver);
	}

	private void doPersPolicyEndProcessing(AcordEventProcessorHelper helper) {
		helper.policy.setPolicyType(helper.lobCdHold);
		helper.lobCdHold = "";
		helper.inPersPolicyNode = false;
	}

	private void doInsuredOrPrincipalEndProcessing(AcordEventProcessorHelper helper) {
		if (helper.isInsuredOrPrincipalRole) {
			helper.policy.setCustomerName(helper.customerNameHold);
		}
		
		helper.customerNameHold = null;
		
		helper.isInsuredOrPrincipalRole = false;
	}

	private void doCurrentTermAmtEndProcessing(AcordEventProcessorHelper helper) {
		if (helper.inPersPolicyNode) {
			helper.policy.setTotalPremium(helper.amtHold);
		}
	}

	private XMLEventReader getEventReaderInstance(String xml) throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		return factory.createXMLEventReader(stream);
	}

}
