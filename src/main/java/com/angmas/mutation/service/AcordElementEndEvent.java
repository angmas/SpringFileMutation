package com.angmas.mutation.service;

import java.util.Map;

import com.google.common.base.Enums;
import com.google.common.collect.Maps;

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
	
	private static final Map<String, AcordElementEndEvent> nameIndex =
	        Maps.newHashMapWithExpectedSize(AcordElementEndEvent.values().length);
	static {
	    for (AcordElementEndEvent element : AcordElementEndEvent.values()) {
	        nameIndex.put(element.name(), element);
	    }
	}
	public static AcordElementEndEvent lookupByName(String name) {
	    return nameIndex.get(name);
	}
}
