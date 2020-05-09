package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class CommercialNameStartEventProcessorImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		
		helper.customerNameHold = helper.getElementData();
	}

}
