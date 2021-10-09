package com.cgm.manager.model.patient;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cgm.manager.annotation.ValidDate;
import com.cgm.manager.util.DateUtils;

import io.swagger.annotations.ApiModelProperty;


public class PatientData {
	@NotBlank(message = "Name is mandatory")
	@ApiModelProperty(value  = "Patient's name", required = true)
	private String name;
	@NotBlank(message = "Surname is mandatory")
	@ApiModelProperty(value  = "Patient's surname", required = true)
	private String surname;
	@NotNull(message = "Birthday is mandatory")
	@ValidDate(message = "Date must to have this format " + DateUtils.DATE_FORMAT, format = DateUtils.DATE_FORMAT)
	@ApiModelProperty(value  = "Patient's birthday", example = DateUtils.DATE_FORMAT, required = true)
	private String birthday;
	@NotBlank(message = "Security-Number is mandatory")
	@ApiModelProperty(value  = "Patient's security number", required = true)
	private String securityNumber;

	
	public PatientData(String name, String surname, String birthday, String securityNumber) {
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.securityNumber = securityNumber;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getSecurityNumber() {
		return securityNumber;
	}


	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}


	@Override
	public String toString() {
		return "Patient [name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", securityNumber="
				+ securityNumber + "]";
	}
}
