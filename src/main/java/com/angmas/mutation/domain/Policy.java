package com.angmas.mutation.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Policy {
	
	private String policyNumber;
	private String customerName;
	private String policyType;
	private BigDecimal totalPremium;
	private List<Vehicle> vehicles;
	private List<Driver> drivers;

	
	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	public Policy() {
		super();
		this.vehicles = new ArrayList<Vehicle>();
		this.drivers = new ArrayList<Driver>();
	}

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


	public String getPolicyNumber() {
		return policyNumber;
	}

	
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}


	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
