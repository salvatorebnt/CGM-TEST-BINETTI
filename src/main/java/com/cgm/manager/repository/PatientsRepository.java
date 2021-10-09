package com.cgm.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgm.manager.entity.PatientEntity;

public interface PatientsRepository extends JpaRepository<PatientEntity, Long>{
}