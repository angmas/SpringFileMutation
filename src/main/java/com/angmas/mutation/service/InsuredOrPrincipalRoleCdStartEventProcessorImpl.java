package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class InsuredOrPrincipalRoleCdStartEventProcessorImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		
		String insuredOrPrincipalRoleCd = helper.getElementData();
		helper.isInsuredOrPrincipalRole = insuredOrPrincipalRoleCd.equalsIgnoreCase("Insured");
	}

}
