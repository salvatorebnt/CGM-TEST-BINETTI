package com.cgm.manager.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cgm.manager.entity.VisitEntity;
import com.cgm.manager.exception.CgmException;
import com.cgm.manager.model.visit.Visit;
import com.cgm.manager.model.visit.VisitData;
import com.cgm.manager.repository.PatientsRepository;
import com.cgm.manager.repository.VisitsRepository;
import com.cgm.manager.service.VisitsService;

@Service
public class VisitsServiceImpl implements VisitsService{
	Logger logger = LoggerFactory.getLogger(VisitsServiceImpl.class);
	
	@Autowired
	VisitsRepository visitsRepository;
	@Autowired
	PatientsRepository patientsRepository;

	@Override
	public Visit insert(VisitData visit) throws CgmException {
		VisitEntity v = null;
		try {
			v = visitsRepository.save(new VisitEntity(visit));
			return new Visit(v);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}

	@Override
	public Visit update(Visit visit) throws NoSuchElementException, CgmException {
		VisitEntity v = null;
		try {
			visitsRepository.findById(visit.getId()).get();
			v = visitsRepository.save(new VisitEntity(visit));
			return new Visit(v);
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
			visitsRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			throw e;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}

	@Override
	public List<Visit> findAll() throws CgmException {
		try {
			List<VisitEntity> list = visitsRepository.findAll();
	        return list.stream().map(temp -> {
	        	Visit v = new Visit(temp);
	            return v;
	        }).collect(Collectors.toList());
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}

	@Override
	public Visit findById(Long id) throws NoSuchElementException, CgmException {
		VisitEntity p = null;
		try {
			p = visitsRepository.findById(id).get();
			return new Visit(p);
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
			return visitsRepository.count();
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}

	@Override
	public void saveAll(List<VisitData> visits) throws CgmException {
		try {
			List<VisitEntity> list = visits.stream().map(temp -> {
				VisitEntity p = new VisitEntity(temp);
	            return p;
	        }).collect(Collectors.toList());
			visitsRepository.saveAll(list);
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}

	@Override
	public List<Visit> findByIdPatient(Long idPatient) throws CgmException {
		try {
			patientsRepository.findById(idPatient).get();
			List<VisitEntity> list = visitsRepository.findByIdPatient(idPatient);
	        return list.stream().map(temp -> {
	        	Visit v = new Visit(temp);
	            return v;
	        }).collect(Collectors.toList());
		}catch (NoSuchElementException e) {
			throw e;
		}catch (Exception e) {
			logger.error(e.getMessage());
			throw new CgmException(e);
		}
	}


}
