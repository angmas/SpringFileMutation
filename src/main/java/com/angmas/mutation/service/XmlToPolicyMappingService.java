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
				event = xmlEventReader.nextEvent();
				policy = new Policy();
	//			policy.setPolicyNumber(event.asCharacters().getData());
				break;
				
			}
	
		}

	private void doEndElementProcessing(XMLEvent event, Policy policy) {
		EndElement endElement = event.asEndElement();
        if (endElement.getName().getLocalPart().equals("PersAutoPolicyQuoteInqRq")) {
            policies.add(policy);
        }		
	}

	private XMLEventReader getEventReaderInstance(String xml) throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		return factory.createXMLEventReader(stream);
	}

}
