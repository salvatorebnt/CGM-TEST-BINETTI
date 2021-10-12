package com.cgm.manager.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;

import com.cgm.manager.exception.CgmException;
import com.cgm.manager.model.visit.Visit;
import com.cgm.manager.model.visit.VisitData;

/**
* Service layer serving patient data 
* @author salvatore.binetti
*/
public interface VisitsService {
	Visit insert(VisitData patient) throws CgmException;
	Visit update(Visit patient) throws NoSuchElementException, CgmException;
	void delete(Long id) throws EmptyResultDataAccessException, CgmException;
	List<Visit> findAll() throws CgmException;
	Visit findById(Long id) throws NoSuchElementException, CgmException;
	long count() throws CgmException;
	void saveAll(List<VisitData> testPatients) throws CgmException;
	List<Visit> findByIdPatient(Long id) throws NoSuchElementException, CgmException;
}
