package com.angmas.mutation.domain;


public class Policy {
	
	private String policyNumber;
	private String customerName;
	private String policyType;

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

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
