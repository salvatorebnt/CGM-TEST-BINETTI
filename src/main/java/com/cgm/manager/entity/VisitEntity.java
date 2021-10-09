package com.cgm.manager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cgm.manager.constants.EntityConstants;
import com.cgm.manager.model.visit.Visit;
import com.cgm.manager.model.visit.VisitData;
import com.cgm.manager.util.DateUtils;

@Entity
@Table(name = EntityConstants.VISIT_TABLE_NAME)
public class VisitEntity {
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = EntityConstants.VISIT_PATIENT_COLUMN_NAME)
	private long idPatient;

	@Column(name = EntityConstants.VISIT_DATE_COLUMN_NAME)
	private Date visitDate;

	@Column(name = EntityConstants.VISIT_TYPE_COLUMN_NAME)
	private String visitType;

	@Column(name = EntityConstants.VISIT_REASON_COLUMN_NAME)
	private String visitReason;
	
	@Column(name = EntityConstants.VISIT_NOTES_NUMBER_COLUMN_NAME)
	private String notes;
	
	public VisitEntity() {
	}
	
	public VisitEntity(long idPatient, Date visitDate, String visitType, String visitReason, String notes) {
		this.idPatient = idPatient;
		this.visitDate = visitDate;
		this.visitType = visitType;
		this.visitReason = visitReason;
		this.notes = notes;
	}
	
	public VisitEntity(long id, long idPatient, Date visitDate, String visitType, String visitReason, String notes) {
		this(idPatient, visitDate, visitType, visitReason, notes);
		this.id = id;
	}
	
	public VisitEntity(VisitData v) {
		this(v.getIdPatient(), DateUtils.stringToDateTime(v.getVisitDate()), v.getVisitType(), v.getVisitReason(), v.getNotes());
	}
	
	public VisitEntity(Visit v){
		this(v.getId(), v.getIdPatient(), DateUtils.stringToDateTime(v.getVisitDate()), v.getVisitType(), v.getVisitReason(), v.getNotes());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(long idPatient) {
		this.idPatient = idPatient;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
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

	@Override
	public String toString() {
		return "VisitEntity [id=" + id + ", idPatient=" + idPatient + ", visitDate=" + visitDate + ", visitType="
				+ visitType + ", visitReason=" + visitReason + ", notes=" + notes + "]";
	}
	

	



}