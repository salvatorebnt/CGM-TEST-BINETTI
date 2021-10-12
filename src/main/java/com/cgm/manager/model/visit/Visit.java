package com.cgm.manager.model.visit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.cgm.manager.entity.VisitEntity;
import com.cgm.manager.util.DateUtils;

import io.swagger.annotations.ApiModelProperty;
/**
 * Visit class data model that extend VisitData class model
 * It is used as response for GET Rest Services and for the input of the UPDATE Rest Service
 * It contains the Visit Identification
 * It include the model validation
 * @author salvatore.binetti
 *
 */
public class Visit extends VisitData{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1466288842107078985L;
	@NotNull(message = "Id is mandatory")
	@Positive(message = "Id must be greather than zero")
	@ApiModelProperty(value  = "Visit's identification", required = true)
	private long id;

	public Visit() {
	}
	
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
