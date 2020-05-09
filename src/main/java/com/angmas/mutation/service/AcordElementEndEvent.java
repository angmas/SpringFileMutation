package com.angmas.mutation.service;

import com.google.common.base.Enums;

public enum AcordElementEndEvent {
	
	CURRENTTERMAMT {

		@Override
		public AcordElementEndEventProcessor getEndEventProcessor() {
			return new CurrentTermAmtEndEventProcessorImpl();
		}

	},
	INSUREDORPRINCIPAL {

		@Override
		public AcordElementEndEventProcessor getEndEventProcessor() {
			return new InsuredOrPrincipalEndEventProcessorImpl();
		}

	},
	PERSAUTOPOLICYQUOTEINQRQ {

		@Override
		public AcordElementEndEventProcessor getEndEventProcessor() {
			return new PersAutoPolicyQuoteInqRqAllEventProcessorImpl();
		}

	},
	PERSDRIVER {

		@Override
		public AcordElementEndEventProcessor getEndEventProcessor() {
			return new PersDriverAllEventProcessorImpl();
		}

	},
	PERSPOLICY {

		@Override
		public AcordElementEndEventProcessor getEndEventProcessor() {
			return new PersPolicyAllEventProcessorImpl();
		}

	},
	PERSVEH {

		@Override
		public AcordElementEndEventProcessor getEndEventProcessor() {
			return new PersVehAllEventProcessorImpl();
		}

		
	};

	public abstract AcordElementEndEventProcessor getEndEventProcessor();
	
	public static AcordElementEndEvent getIfPresent(String name) {
		return Enums.getIfPresent(AcordElementEndEvent.class, name).orNull();
	}
}
