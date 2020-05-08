package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public interface AcordElementStartEventProcessor {

	void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException;
	
}
