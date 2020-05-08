package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class ModelStartEventProcessingImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.vehicle.setModel(helper.getElementData());	
	}

}
