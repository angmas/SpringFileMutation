package com.angmas.mutation.service;

import java.util.Map;

import com.google.common.base.Enums;
import com.google.common.collect.Maps;

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
	
	private static final Map<String, AcordElementStartEvent> nameIndex =
	        Maps.newHashMapWithExpectedSize(AcordElementStartEvent.values().length);
	static {
	    for (AcordElementStartEvent element : AcordElementStartEvent.values()) {
	        nameIndex.put(element.name(), element);
	    }
	}
	public static AcordElementStartEvent lookupByName(String name) {
	    return nameIndex.get(name);
	}
	
}
