package com.cgm.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgm.manager.entity.VisitEntity;

public interface VisitsRepository extends JpaRepository<VisitEntity, Long>{

	List<VisitEntity> findByIdPatient(Long idPatient);

}