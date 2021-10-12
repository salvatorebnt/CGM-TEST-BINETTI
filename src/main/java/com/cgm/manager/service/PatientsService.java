package com.cgm.manager.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.cgm.manager.exception.CgmException;
import com.cgm.manager.model.patient.Patient;
import com.cgm.manager.model.patient.PatientData;

/**
* Service layer serving patient data 
* @author salvatore.binetti
*/
public interface PatientsService {
	Patient insert(PatientData patient) throws CgmException;
	Patient update(Patient patient) throws NoSuchElementException, CgmException;
	void delete(Long id) throws EmptyResultDataAccessException, CgmException;
	List<Patient> findAll() throws CgmException;
	Patient findById(Long id) throws NoSuchElementException, CgmException;
	long count() throws CgmException;
	void saveAll(List<PatientData> testPatients) throws CgmException;
}
