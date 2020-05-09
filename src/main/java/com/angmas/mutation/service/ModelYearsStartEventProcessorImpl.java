package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class ModelYearsStartEventProcessorImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		
		helper.vehicle.setModelYear(helper.getElementData());
	}

}
