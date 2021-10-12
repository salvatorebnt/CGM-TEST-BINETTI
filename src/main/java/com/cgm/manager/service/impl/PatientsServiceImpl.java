package com.cgm.manager.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cgm.manager.entity.PatientEntity;
import com.cgm.manager.exception.CgmException;
import com.cgm.manager.model.patient.Patient;
import com.cgm.manager.model.patient.PatientData;
import com.cgm.manager.repository.PatientsRepository;
import com.cgm.manager.service.PatientsService;

@Service
public class PatientsServiceImpl implements PatientsService{
	Logger logger = LoggerFactory.getLogger(PatientsServiceImpl.class);
	
	@Autowired
	PatientsRepository patientRepository;

	@Override
	public Patient insert(PatientData patientData) throws CgmException {
		PatientEntity p = null;
		try {
			p = patientRepository.save(new PatientEntity(patientData));
			return new Patient(p);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}
	
	@Override
	public Patient update(Patient patient) throws NoSuchElementException, CgmException {
		PatientEntity p = null;
		try {
			patientRepository.findById(patient.getId()).get();
			p = patientRepository.save(new PatientEntity(patient));
			return new Patient(p);
		}catch (NoSuchElementException e) {
			throw e;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}
	
	@Override
	public void delete(Long id) throws EmptyResultDataAccessException, CgmException {
		try {
			patientRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw e;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}
	
	@Override
	public List<Patient> findAll() throws CgmException {
		try {
			List<PatientEntity> list = patientRepository.findAll();
	        return list.stream().map(temp -> {
	        	Patient p = new Patient(temp);
	            return p;
	        }).collect(Collectors.toList());
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}

	@Override
	public Patient findById(Long id) throws NoSuchElementException, CgmException {
		PatientEntity p = null;
		try {
			p = patientRepository.findById(id).get();
			return new Patient(p);
		}catch (NoSuchElementException e) {
			throw e;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}

	@Override
	public long count() throws CgmException {
		try {
			return patientRepository.count();
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}

	@Override
	public void saveAll(List<PatientData> patients) throws CgmException {
		try {
			List<PatientEntity> list = patients.stream().map(temp -> {
				PatientEntity p = new PatientEntity(temp);
	            return p;
	        }).collect(Collectors.toList());
			patientRepository.saveAll(list);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}
}
