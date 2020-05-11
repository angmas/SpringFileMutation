package com.angmas.mutation.service;

import com.angmas.mutation.common.CurrencyFormatter;

import javax.xml.stream.XMLStreamException;

public class CurrentTermAmtEndEventProcessorImpl implements AcordElementEndEventProcessor {

	@Override
	public void doEndProcessing(AcordEventProcessorHelper helper) {
		if (helper.inPersPolicyNode) {

			helper.policy.setTotalPremium(CurrencyFormatter.formatStringToNumber(helper.amtHold));
		}
	}

}
