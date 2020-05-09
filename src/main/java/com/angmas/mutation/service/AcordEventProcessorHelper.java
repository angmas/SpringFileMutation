/***
 * This class holds the properties needed to completely map a Policy object from ACORD XML
 */
package com.angmas.mutation.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import com.angmas.mutation.domain.Driver;
import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.domain.Vehicle;

public class AcordEventProcessorHelper {
	
	// Stax objects
	public XMLEventReader xmlEventReader;
	public XMLEvent event;
	public StartElement startElement;
	public EndElement endElement;

	// Booleans for decision making
	public boolean inPersPolicyNode;
	public boolean isInsuredOrPrincipalRole;

	// temp hold area
	public String amtHold;
	public String customerNameHold;
	public String lobCdHold;
	
	// Domain objects used to build the policy object
	public List<Policy> policies;
	public Policy policy;
	public Vehicle vehicle;
	public Driver driver;
	
	
	
	public AcordEventProcessorHelper() {
		this.policies = new ArrayList<>();
	}
	
	public void setNextEvent() throws XMLStreamException {
		event = xmlEventReader.nextEvent();
	}
	
	public String getElementData() {
		return event.asCharacters().getData();
	}
	
	public void setStartElement() {
		startElement = event.asStartElement();
	}
	
	public void setEndElement() {
		endElement = event.asEndElement();
	}
	public String getStartElementName() {
		return startElement.getName().getLocalPart();
	}
	
	public String getEndElementName() {
		return endElement.getName().getLocalPart();
	}
	
}