package com.cgm.manager.model.visit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.cgm.manager.annotation.ValidDate;
import com.cgm.manager.annotation.ValidValueOfEnum;
import com.cgm.manager.util.DateUtils;

import io.swagger.annotations.ApiModelProperty;

public class VisitData {
	@NotNull(message = "Id patiend is mandatory")
	@Positive(message = "Id patient must be greather than zero")
	@ApiModelProperty(value  = "Patient's identification", required = true)
	private long idPatient;
	
	@NotNull(message = "Visit date and time is mandatory")
	@ValidDate(message = "Date must to have this format " + DateUtils.DATE_TIME_FORMAT, format = DateUtils.DATE_TIME_FORMAT)
	@ApiModelProperty(value  = "Visit's date and time", example = DateUtils.DATE_TIME_FORMAT, required = true)
	private String visitDate;
	
	@NotNull(message = "Visit type is mandatory")
	@ValidValueOfEnum(enumClass = VisitTypeEnum.class)
	@ApiModelProperty(value  = "Visit type", example = "Home/Office", required = true)
	private String visitType;
	
	@NotNull(message = "Visit reason is mandatory")
	@ValidValueOfEnum(enumClass = VisitReasonEnum.class)
	@ApiModelProperty(value  = "Visit reason", example = "First visit/Recurring visit/Urgent", required = true)
	private String visitReason;
	
	@Size(min = 0, max = 255, message = "Notes must be max 255 characters")
	@ApiModelProperty(value  = "Free text section about patient family's history")
	private String notes;

	
	public VisitData(long idPatient, String visitDate, String visitType, String visitReason, String notes) {
		this.idPatient = idPatient;
		this.visitDate = visitDate;
		this.visitType = visitType;
		this.visitReason = visitReason;
		this.notes = notes;
	}

	public long getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(long idPatient) {
		this.idPatient = idPatient;
	}

	public String getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(String visitDate) {
		this.visitDate = visitDate;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getVisitReason() {
		return visitReason;
	}

	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
