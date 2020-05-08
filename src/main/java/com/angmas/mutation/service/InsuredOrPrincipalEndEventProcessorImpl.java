package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class InsuredOrPrincipalEndEventProcessorImpl implements AcordElementEndEventProcessor {

	@Override
	public void doEndProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		if (helper.isInsuredOrPrincipalRole) {
			helper.policy.setCustomerName(helper.customerNameHold);
		}
		
		helper.customerNameHold = null;
		
		helper.isInsuredOrPrincipalRole = false;
	}

}
