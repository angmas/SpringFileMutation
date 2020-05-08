package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class ManufacturerStartEventProcessingImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.vehicle.setMake(helper.getElementData());
	}

}
