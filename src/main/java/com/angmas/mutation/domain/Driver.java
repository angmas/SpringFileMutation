package com.angmas.mutation.domain;


import com.angmas.mutation.common.AgeCalculator;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Driver {
	
	private String id;
	private String driverName;
	@JsonIgnore
	private LocalDate birthDate;

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAge() {
		return AgeCalculator.getDifferenceInYears(birthDate);
	}
	
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
