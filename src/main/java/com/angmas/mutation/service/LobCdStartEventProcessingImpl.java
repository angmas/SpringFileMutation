package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class LobCdStartEventProcessingImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.lobCdHold = helper.getElementData();
	}

}
