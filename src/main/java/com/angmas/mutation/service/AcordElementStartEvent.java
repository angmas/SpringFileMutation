package com.angmas.mutation.service;

import com.google.common.base.Enums;

public enum AcordElementStartEvent {
	
	AMT {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new AmtStartEventProcessorImpl();
		}

		
	},
	BIRTHDT {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new BirthDtStartEventProcessorImpl();
		}

		
	},
	COMMERCIALNAME {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new CommercialNameStartEventProcessorImpl();
		}

		
	},
	INSUREDORPRINCIPALROLECD {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new InsuredOrPrincipalRoleCdStartEventProcessorImpl();
		}

		
	},
	LOBCD {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new LobCdStartEventProcessorImpl();		}

		
	},
	MANUFACTURER {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new ManufacturerStartEventProcessorImpl();
		}

	},
	MODEL {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new ModelStartEventProcessorImpl();
		}

		
	},
	MODELYEAR {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new ModelYearsStartEventProcessorImpl();
		}

		
	},
	PERSAUTOPOLICYQUOTEINQRQ {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new PersAutoPolicyQuoteInqRqAllEventProcessorImpl();
		}

		
	},
	PERSDRIVER {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new PersDriverAllEventProcessorImpl();
		}

		
	},
	PERSPOLICY {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new PersPolicyAllEventProcessorImpl();
		}

		
	},
	PERSVEH {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new PersVehAllEventProcessorImpl();
		}

		
	},
	POLICYNUMBER {

		@Override
		public AcordElementStartEventProcessor getStartEventProcessor() {
			return new PolicyNumberStartEventProcessorImpl(); 
		}

		
	};
	
	public abstract AcordElementStartEventProcessor getStartEventProcessor();
	
	public static AcordElementStartEvent getIfPresent(String name) {
		return Enums.getIfPresent(AcordElementStartEvent.class, name).orNull();
	}
	
}
