package com.pm.patient_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getAllPatientsResponseDtos() {
        List<Patient> patients = patientRepository.findAll();
        
        List<PatientResponseDto> allPatientsResponseDtos = patients.stream()
                .map(PatientMapper::toPatientResponseDto)
                .toList();
                
        return allPatientsResponseDtos;
    }
}
