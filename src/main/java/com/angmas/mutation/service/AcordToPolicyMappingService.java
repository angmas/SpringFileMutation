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
	private AcordStartEventProcessorFactory acordStartFactory;
	private AcordEndEventProcessorFactory acordEndFactory;
	
	public AcordToPolicyMappingService() {
		this.helper = new AcordEventProcessorHelper();
		acordStartFactory = new AcordStartEventProcessorFactory();
		acordEndFactory = new AcordEndEventProcessorFactory();
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
		
		AcordElementStartEventProcessor elementStartProcessor = acordStartFactory.getProcessor(helper.getStartElementName());
		
		helper.setNextEvent();
		
		if (elementStartProcessor != null) {
			elementStartProcessor.doStartProcessing(helper);
		}
		
	}

	private void doEndElementProcessing() throws XMLStreamException {
		helper.setEndElement();
		AcordElementEndEventProcessor elementEndProcessor = acordEndFactory.getProcessor(helper.getEndElementName());
		
		if (elementEndProcessor != null) {
			elementEndProcessor.doEndProcessing(helper);
		}
	}
	
	private XMLEventReader getEventReaderInstance(String xml) throws FactoryConfigurationError, XMLStreamException {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		InputStream stream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));
		return factory.createXMLEventReader(stream);
	}

}
