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
		
		AcordElementStartEvent acordElementStartEvent = AcordElementStartEvent.getIfPresent(helper.getStartElementName().toUpperCase());

		helper.setNextEvent();
		
		if (acordElementStartEvent != null) {
			AcordElementStartEventProcessor elementStartProcessor = acordElementStartEvent.getStartEventProcessor();
			elementStartProcessor.doStartProcessing(helper);
		}
		
	}

	private void doEndElementProcessing() throws XMLStreamException {
		helper.setEndElement();
		AcordElementEndEvent acordElementEndEvent = AcordElementEndEvent.getIfPresent(helper.getEndElementName().toUpperCase());
		if (acordElementEndEvent != null) {
			AcordElementEndEventProcessor elementEndProcessor = acordElementEndEvent.getEndEventProcessor();
			elementEndProcessor.doEndProcessing(helper);
		}
	}
	
	private XMLEventReader getEventReaderInstance(String xml) throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		return factory.createXMLEventReader(stream);
	}

}
