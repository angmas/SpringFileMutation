package com.angmas.mutation.service;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.xml.stream.XMLStreamConstants;
import com.angmas.mutation.domain.Policy;

public class AcordToPolicyMappingService {

	private AcordEventProcessorHelper helper;
	
	public AcordToPolicyMappingService() {
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

		helper.setStartElement();
		helper.setNextEvent();
		
		try {
			AcordElementStartEvent acordElementStart = AcordElementStartEvent.valueOf(helper.startElementName);
			acordElementStart.doStartProcessing(helper);
		}
		catch (IllegalArgumentException  e) {
			// do nothing if element is not in enum list
		}
		
		
	}

	private void doEndElementProcessing() throws XMLStreamException {
		
		helper.setEndElement();
		
		try {
			AcordElementEndEvent acordElementEnd = AcordElementEndEvent.valueOf(helper.endElementName);
			acordElementEnd.doEndProcessing(helper);
		}
		catch (IllegalArgumentException  e) {
			// do nothing if element is not in enum list
		}
	}
	
	private XMLEventReader getEventReaderInstance(String xml) throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		return factory.createXMLEventReader(stream);
	}

}