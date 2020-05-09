package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class ManufacturerStartEventProcessorImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		
		helper.vehicle.setMake(helper.getElementData());
	}

}
