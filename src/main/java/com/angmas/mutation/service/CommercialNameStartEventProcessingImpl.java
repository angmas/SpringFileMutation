package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class CommercialNameStartEventProcessingImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.customerNameHold = helper.getElementData();
	}

}
