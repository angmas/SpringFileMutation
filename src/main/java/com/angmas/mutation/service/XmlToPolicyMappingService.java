package com.angmas.mutation.service;

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
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;



import com.angmas.mutation.domain.Policy;

public class XmlToPolicyMappingService {

	private XMLEventReader xmlEventReader;
	private List<Policy> policies;
	private Policy policy;
	private String customerNameHold;
	private boolean isInsuredOrPrincipalRole;
	private String lobCdHold;
	private boolean inPersPolicyNode;
	private String amtHold;
	
	public XmlToPolicyMappingService() {
		super();
		this.policies = new ArrayList<>();
	}

	public List<Policy> mapPolicies(String xmlString) throws XMLStreamException {
		this.xmlEventReader = getEventReaderInstance(xmlString);
		processXmlElements();
		return policies;
	}

	private void processXmlElements() throws XMLStreamException {
		while (xmlEventReader.hasNext()) {
			XMLEvent event = xmlEventReader.nextEvent();
			switch (event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					doStartElementProcessing(event);
					break;
				case XMLStreamConstants.END_ELEMENT:
					doEndElementProcessing(event, policy);
					break;
//				default:
//					this.xmlEventWriter.add(event);
//					break;
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
			}
	
		}

	private void doAmtStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		amtHold = event.asCharacters().getData();
	}

	private void doLOBCdStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		lobCdHold = event.asCharacters().getData();
	}

	private void doInsuredOrPrincipalRoleCdStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		String insuredOrPrincipalRoleCd = event.asCharacters().getData();
		isInsuredOrPrincipalRole = insuredOrPrincipalRoleCd.equalsIgnoreCase("Insured");
	}

	private void doCommercialNameStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		customerNameHold = event.asCharacters().getData();
	}

	private void doPolicyNumberStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		policy.setPolicyNumber(event.asCharacters().getData());
	}

	private void doPersPolicyStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		inPersPolicyNode = true;
	}

	private void doPersAutoPolicyQuoteInsRqStartProcessing(XMLEvent event) throws XMLStreamException {
		event = xmlEventReader.nextEvent();
		policy = new Policy();
	}

	private void doEndElementProcessing(XMLEvent event, Policy policy) {
		EndElement endElement = event.asEndElement();
		switch (endElement.getName().getLocalPart()) {
		case "PersAutoPolicyQuoteInqRq":
			policies.add(policy);
			break;
		case "InsuredOrPrincipal":
			doInsuredOrPrincipalEndProcessing(policy);
			break;
		case "PersPolicy":
			doPersPolicyEndProcessing(policy);
			break;
		case "CurrentTermAmt":
			doCurrentTermAmtEndProcessing();
			break;
		}
	}

	private void doCurrentTermAmtEndProcessing() {
		if (inPersPolicyNode) {
			policy.setTotalPremium(amtHold);
		}
	}

	private void doInsuredOrPrincipalEndProcessing(Policy policy) {
		if (isInsuredOrPrincipalRole) {
			policy.setCustomerName(customerNameHold);
		} else {
			customerNameHold = "";
		}
		
		isInsuredOrPrincipalRole = false;
	}

	private void doPersPolicyEndProcessing(Policy policy) {
		policy.setPolicyType(lobCdHold);
		lobCdHold = "";
		inPersPolicyNode = false;
	}
	

	private XMLEventReader getEventReaderInstance(String xml) throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		return factory.createXMLEventReader(stream);
	}

}
