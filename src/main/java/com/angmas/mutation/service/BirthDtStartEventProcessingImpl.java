package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class BirthDtStartEventProcessingImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.driver.setBirthDate(helper.getElementData());
	}

}
