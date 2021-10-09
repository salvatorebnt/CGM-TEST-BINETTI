package com.cgm.manager.model.visit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.cgm.manager.entity.VisitEntity;
import com.cgm.manager.util.DateUtils;

import io.swagger.annotations.ApiModelProperty;

public class Visit extends VisitData{
	@NotNull(message = "Id is mandatory")
	@Positive(message = "Id must be greather than zero")
	@ApiModelProperty(value  = "Visit's identification", required = true)
	private long id;

	
	public Visit(long id, long idPatient, String visitDate, String visitType, String visitReason, String notes) {
		super(idPatient, visitDate, visitType, visitReason, notes);
		this.id = id;
	}
	
	public Visit(VisitEntity v){
		this(v.getId(), v.getIdPatient(), DateUtils.dateTimeToString(v.getVisitDate()), v.getVisitType(), v.getVisitReason(), v.getNotes());
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
