package com.angmas.mutation.service;

public class AcordEndEventProcessorFactory {
	
	public AcordElementEndEventProcessor getProcessor(String acordNodeName) {
		switch (acordNodeName) {
		case "PersAutoPolicyQuoteInqRq":
			return new PersAutoPolicyQuoteInqRqAllEventProcessorImpl();
			
		case "InsuredOrPrincipal":
			return new InsuredOrPrincipalEndEventProcessorImpl();
			
		case "PersPolicy":
			return new PersPolicyAllEventProcessorImpl();
		
		case "CurrentTermAmt":
			return new CurrentTermAmtEndEventProcessorImpl();
			
		case "PersVeh":
			return new PersVehAllEventProcessorImpl();
			
		case "PersDriver":
			return new PersDriverAllEventProcessorImpl();
			
		default:
			return null;
		}
	
	}
}
