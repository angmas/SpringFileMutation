package com.angmas.mutation.service;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;

import com.angmas.mutation.domain.Vehicle;

public class PersVehAllEventProcessorImpl implements AcordElementStartEventProcessor, AcordElementEndEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.setNextEvent();
		helper.vehicle = new Vehicle();
		Attribute id = helper.startElement.getAttributeByName(new QName("id"));
		helper.vehicle.setId(id.getValue());
	}

	@Override
	public void doEndProcessing(AcordEventProcessorHelper helper) throws XMLStreamException {
		helper.policy.getVehicles().add(helper.vehicle);
	}

}
