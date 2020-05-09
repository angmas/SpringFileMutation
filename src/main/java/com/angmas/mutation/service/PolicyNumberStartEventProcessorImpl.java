package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class PolicyNumberStartEventProcessorImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		
		helper.policy.setPolicyNumber(helper.getElementData());
	}

}
