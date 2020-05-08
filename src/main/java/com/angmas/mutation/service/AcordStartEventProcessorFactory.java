package com.angmas.mutation.service;

public class AcordStartEventProcessorFactory {
	
	public AcordElementStartEventProcessor getProcessor(String acordNodeName) {
		switch (acordNodeName) {
		case "PersAutoPolicyQuoteInqRq":
			return new PersAutoPolicyQuoteInqRqAllEventProcessorImpl();
			
		case "PersPolicy":
			return new PersPolicyAllEventProcessorImpl();
			
		case "PolicyNumber":
			return new PolicyNumberStartEventProcessorImpl(); 
			
		case "CommercialName":
			return new CommercialNameStartEventProcessorImpl();
			
		case "InsuredOrPrincipalRoleCd":
			return new InsuredOrPrincipalRoleCdStartEventProcessorImpl();
			
		case "LOBCd":
			return new LobCdStartEventProcessorImpl();
			
		case "Amt":
			return new AmtStartEventProcessorImpl();
			
		case "PersVeh":
			return new PersVehAllEventProcessorImpl();
			
		case "PersDriver":
			return new PersDriverAllEventProcessorImpl();
			
		case "Manufacturer":
			return new ManufacturerStartEventProcessorImpl();
			
		case "Model":
			return new ModelStartEventProcessorImpl();
			
		case "ModelYear":
			return new ModelYearsStartEventProcessorImpl();
			
		case "BirthDt":
			return new BirthDtStartEventProcessorImpl();
		default:
			return null;
		}
	
	}
}
