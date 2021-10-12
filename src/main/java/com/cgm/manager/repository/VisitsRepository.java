package com.cgm.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgm.manager.entity.VisitEntity;

/**
 * VisitsRepository extending JpaRepository
 * Needed to complete the basic CRUD operations on VisitEntity
 * @author salvatore.binetti
 *
 */
@Repository
public interface VisitsRepository extends JpaRepository<VisitEntity, Long>{

	/**
	 * Find Visits by patient identification
	 * @param idPatient: Long
	 * @return List<VisitEntity>
	 * @return
	 */
	List<VisitEntity> findByIdPatient(Long idPatient);

}