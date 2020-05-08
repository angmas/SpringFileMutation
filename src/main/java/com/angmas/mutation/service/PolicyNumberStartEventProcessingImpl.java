package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class PolicyNumberStartEventProcessingImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.policy.setPolicyNumber(helper.getElementData());
	}

}
