/***
 * This class holds the properties needed to completely map a Policy object from ACORD XML
 */
package com.angmas.mutation.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.XMLEvent;

import com.angmas.mutation.domain.Driver;
import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.domain.Vehicle;

public class AcordEventProcessorHelper {
	
	public XMLEventReader xmlEventReader;
	public XMLEvent event;
	public List<Policy> policies;
	public Policy policy;
	public String customerNameHold;
	public boolean isInsuredOrPrincipalRole;
	public String lobCdHold;
	public boolean inPersPolicyNode;
	public String amtHold;
	public Vehicle vehicle;
	public Driver driver;
	
	public AcordEventProcessorHelper() {
		this.policies = new ArrayList<>();
	}
	
	
}
