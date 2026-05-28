package com.pm.patient_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.patient_service.model.Patient;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {}