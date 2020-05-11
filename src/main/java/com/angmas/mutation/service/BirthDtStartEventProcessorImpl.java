package com.angmas.mutation.service;

import com.angmas.mutation.common.DateFormatter;

import javax.xml.stream.XMLStreamException;

public class BirthDtStartEventProcessorImpl implements AcordElementStartEventProcessor {

	@Override
	public void doStartProcessing(AcordEventProcessorHelper helper) {
		
		helper.driver.setBirthDate(DateFormatter.formatStringToDate(helper.getElementData()));
	}

}
