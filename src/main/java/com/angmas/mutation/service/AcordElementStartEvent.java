package com.angmas.mutation.service;

import javax.xml.namespace.QName;
import javax.xml.stream.events.Attribute;

import com.angmas.mutation.domain.Driver;
import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.domain.Vehicle;

public enum AcordElementStartEvent {
	
	AMT {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.amtHold = helper.getElementData();			
		}
		
	},
	BIRTHDT {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.driver.setBirthDate(helper.getElementData());			
		}
		
	},
	COMMERCIALNAME {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.customerNameHold = helper.getElementData();
		}
		
	},
	INSUREDORPRINCIPALROLECD {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			String insuredOrPrincipalRoleCd = helper.getElementData();
			helper.isInsuredOrPrincipalRole = insuredOrPrincipalRoleCd.equalsIgnoreCase("Insured");			
		}
		
	},
	LOBCD {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.lobCdHold = helper.getElementData();
		}
		
	},
	MANUFACTURER {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.vehicle.setMake(helper.getElementData());
		}
		
	},
	MODEL {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.vehicle.setModel(helper.getElementData());	
		}
		
	},
	MODELYEAR {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.vehicle.setModelYear(helper.getElementData());
		}
		
	},
	PERSAUTOPOLICYQUOTEINQRQ {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.policy = new Policy();
		}
		
	},
	PERSDRIVER {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.driver = new Driver();
			Attribute id = helper.startElement.getAttributeByName(new QName("id"));
			helper.driver.setId(id.getValue());			
		}
		
	},
	PERSPOLICY {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.inPersPolicyNode = true;			
		}
		
	},
	PERSVEH {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.vehicle = new Vehicle();
			Attribute id = helper.startElement.getAttributeByName(new QName("id"));
			helper.vehicle.setId(id.getValue());			
		}
		
	},
	POLICYNUMBER {

		@Override
		public void doStartProcessing(AcordEventProcessorHelper helper) {
			helper.policy.setPolicyNumber(helper.getElementData());
		}
		
	};
	
	private AcordElementStartEvent() {};
	public abstract void doStartProcessing(AcordEventProcessorHelper helper);
}
