package com.cgm.manager.model.patient;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.cgm.manager.entity.PatientEntity;
import com.cgm.manager.util.DateUtils;

import io.swagger.annotations.ApiModelProperty;

public class Patient extends PatientData{
	@NotNull(message = "Id is mandatory")
	@Positive(message = "Id must be greather than zero")
	@ApiModelProperty(value  = "Patient's identification", required = true)
	private long id;
	
	public Patient(long id, String name, String surname, String birthday, String securityNumber) {
		super(name, surname, birthday, securityNumber);
		this.id = id;
	}
	
	public Patient(PatientEntity pe){
		this(pe.getId(), pe.getName(), pe.getSurname(), DateUtils.dateToString(pe.getBirthday()), pe.getSecurityNumber());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
