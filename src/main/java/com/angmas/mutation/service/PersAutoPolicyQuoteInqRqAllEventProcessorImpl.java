package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

import com.angmas.mutation.domain.Policy;

public class PersAutoPolicyQuoteInqRqAllEventProcessorImpl implements AcordElementStartEventProcessor, AcordElementEndEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.policy = new Policy();

	}

	@Override
	public void doEndProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.policies.add(helper.policy);		
	}

}
