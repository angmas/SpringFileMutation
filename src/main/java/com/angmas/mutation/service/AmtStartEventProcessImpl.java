package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class AmtStartEventProcessImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.amtHold = helper.getElementData();
	}

}
