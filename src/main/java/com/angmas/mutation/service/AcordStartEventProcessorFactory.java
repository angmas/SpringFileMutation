package com.angmas.mutation.service;

public class AcordStartEventProcessorFactory {
	
	public AcordElementStartEventProcessor getProcessor(String acordNodeName) {
		switch (acordNodeName) {
		case "PersAutoPolicyQuoteInqRq":
			return new PersAutoPolicyQuoteInqRqEventProcessorImpl();
			
		case "PersPolicy":
			return new PersPolicyEventProcessorImpl();
			
		case "PolicyNumber":
			return new PolicyNumberStartEventProcessingImpl(); 
			
		case "CommercialName":
			return new CommercialNameStartEventProcessingImpl();
			
		case "InsuredOrPrincipalRoleCd":
			return new InsuredOrPrincipalRoleCdStartEventProcessingImpl();
			
		case "LOBCd":
			return new LobCdStartEventProcessingImpl();
			
		case "Amt":
			return new AmtStartEventProcessImpl();
			
		case "PersVeh":
			return new PersVehEventProcessorImpl();
			
		case "PersDriver":
			return new PersDriverEventProcessorImpl();
			
		case "Manufacturer":
			return new ManufacturerStartEventProcessingImpl();
			
		case "Model":
			return new ModelStartEventProcessingImpl();
			
		case "ModelYear":
			return new ModelYearsStartEventProcessingImpl();
			
		case "BirthDt":
			return new BirthDtStartEventProcessingImpl();
		default:
			return null;
		}
	
	}
}
