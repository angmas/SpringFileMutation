package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class PersPolicyEventProcessorImpl implements AcordElementEndEventProcessor, AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.inPersPolicyNode = true;
	}

	@Override
	public void doEndProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.policy.setPolicyType(helper.lobCdHold);
		helper.lobCdHold = "";
		helper.inPersPolicyNode = false;
	}

}