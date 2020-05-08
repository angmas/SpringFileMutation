package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public interface AcordElementEndEventProcessor {

	void doEndProcessing(AcordEventProcessorHelper helper) throws XMLStreamException;
	
}
