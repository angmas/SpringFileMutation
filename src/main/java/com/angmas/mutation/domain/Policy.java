package com.angmas.mutation.domain;

import java.math.BigDecimal;

public class Policy {
	
	private String policyNumber;
	private String customerName;
	private String policyType;
	private BigDecimal totalPremium; 

	public BigDecimal getTotalPremium() {
		return totalPremium.setScale(2);
	}

	public void setTotalPremium(String amt) {
		try {
			BigDecimal bigDecimalamt = new BigDecimal(amt);
			this.totalPremium = bigDecimalamt;
		} catch (Exception e) {
			this.totalPremium = null;
		}
	}

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
