package com.cgm.manager.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cgm.manager.util.DateUtils;
import com.cgm.manager.constants.EntityConstants;
import com.cgm.manager.model.patient.Patient;
import com.cgm.manager.model.patient.PatientData;

@Entity
@Table(name = EntityConstants.PATIENT_TABLE_NAME)
public class PatientEntity {
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = EntityConstants.PATIENT_NAME_COLUMN_NAME)
	private String name;

	@Column(name = EntityConstants.PATIENT_SURNAME_COLUMN_NAME)
	private String surname;

	@Column(name = EntityConstants.PATIENT_BIRTHDAY_COLUMN_NAME)
	private Date birthday;

	@Column(name = EntityConstants.PATIENT_SECURITY_NUMBER_COLUMN_NAME)
	private String securityNumber;
	
	public PatientEntity() {

	}
	
	public PatientEntity(String name, String surname, Date birthday, String securityNumber) {
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.securityNumber = securityNumber;
	}

	public PatientEntity(long id, String name, String surname, Date birthday, String securityNumber) {
		this(name, surname, birthday, securityNumber);
		this.id = id;
	}
	
	public PatientEntity(PatientData p){
		this(p.getName(), p.getSurname(), DateUtils.stringToDate(p.getBirthday()), p.getSecurityNumber());
	}
	
	public PatientEntity(Patient p){
		this(p.getId(), p.getName(), p.getSurname(), DateUtils.stringToDate(p.getBirthday()), p.getSecurityNumber());
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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
		return "Patient [id=" + id + ", name=" + name + ", surname=" + surname + ", birthday=" + birthday
				+ ", securityNumber=" + securityNumber + "]";
	}



}