package com.angmas.mutation.domain;


public class Policy {
	
	private String policyNumber;
	private String customerName;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Policy() {
		super();
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

}
