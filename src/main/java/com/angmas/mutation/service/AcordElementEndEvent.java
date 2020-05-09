package com.angmas.mutation.service;

public enum AcordElementEndEvent {
	
	CURRENTTERMAMT {

		@Override
		public void doEndProcessing(AcordEventProcessorHelper helper) {
			if (helper.inPersPolicyNode) {
				helper.policy.setTotalPremium(helper.amtHold);
			}			
		}
	},
	INSUREDORPRINCIPAL {

		@Override
		public void doEndProcessing(AcordEventProcessorHelper helper) {
			if (helper.isInsuredOrPrincipalRole) {
				helper.policy.setCustomerName(helper.customerNameHold);
			}
			
			helper.customerNameHold = null;
			
			helper.isInsuredOrPrincipalRole = false;			
		}
	},
	PERSAUTOPOLICYQUOTEINQRQ {

		@Override
		public void doEndProcessing(AcordEventProcessorHelper helper) {
			helper.policies.add(helper.policy);		
		}
	},
	PERSDRIVER {

		@Override
		public void doEndProcessing(AcordEventProcessorHelper helper) {
			helper.driver.setDriverName(helper.customerNameHold);
			helper.customerNameHold = null;
			helper.policy.getDrivers().add(helper.driver);			
		}
	},
	PERSPOLICY {

		@Override
		public void doEndProcessing(AcordEventProcessorHelper helper) {
			helper.policy.setPolicyType(helper.lobCdHold);
			helper.lobCdHold = "";
			helper.inPersPolicyNode = false;			
		}
	},
	PERSVEH {

		@Override
		public void doEndProcessing(AcordEventProcessorHelper helper) {
			helper.policy.getVehicles().add(helper.vehicle);
		}
		
	};

	public abstract void doEndProcessing(AcordEventProcessorHelper helper);
}
