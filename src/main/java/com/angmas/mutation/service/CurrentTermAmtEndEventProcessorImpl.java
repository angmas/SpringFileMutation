package com.angmas.mutation.service;

import javax.xml.stream.XMLStreamException;

public class CurrentTermAmtEndEventProcessorImpl implements AcordElementEndEventProcessor {

	@Override
	public void doEndProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		if (helper.inPersPolicyNode) {
			helper.policy.setTotalPremium(helper.amtHold);
		}
	}

}
