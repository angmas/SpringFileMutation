package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class LobCdStartEventProcessorImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		
		helper.lobCdHold = helper.getElementData();
	}

}
