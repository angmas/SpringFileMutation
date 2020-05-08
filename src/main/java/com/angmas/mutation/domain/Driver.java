package com.angmas.mutation.domain;


import org.joda.time.LocalDate;
import org.joda.time.Years;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Driver {
	
	private String id;
	private String driverName;
	@JsonIgnore
	private LocalDate birthDate;

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String string) {
		this.birthDate = new LocalDate(string);
	}

	public int getAge() {
		Years age = Years.yearsBetween(birthDate, LocalDate.now());
		return age.getYears();
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
