package com.cgm.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgm.manager.entity.PatientEntity;

/**
 * PatientRepository extending JpaRepository
 * Needed to complete the basic CRUD operations on PatientEntity
 * @author salvatore.binetti
 *
 */
@Repository
public interface PatientsRepository extends JpaRepository<PatientEntity, Long>{
}