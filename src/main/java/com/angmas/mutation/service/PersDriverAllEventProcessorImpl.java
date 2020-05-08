package com.angmas.mutation.service;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;

import com.angmas.mutation.domain.Driver;

public class PersDriverAllEventProcessorImpl implements AcordElementEndEventProcessor, AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.driver = new Driver();
		Attribute id = helper.startElement.getAttributeByName(new QName("id"));
		helper.driver.setId(id.getValue());		
	}

	@Override
	public void doEndProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.driver.setDriverName(helper.customerNameHold);
		helper.customerNameHold = null;
		helper.policy.getDrivers().add(helper.driver);
	}

}
