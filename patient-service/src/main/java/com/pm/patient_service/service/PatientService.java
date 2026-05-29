package com.pm.patient_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true )
    public List<PatientResponseDto> getAllPatientsFromRepository() {
        List<Patient> allPatients = patientRepository.findAll();
        
        return allPatients.stream()
                .map(PatientMapper::toPatientResponseDto)
                .toList();
    }

    @Transactional
    public PatientResponseDto savePatientToRepository(PatientRequestDto patientRequestDto) {        
        if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("Cannot create the patient. A patient with this email already exists: " + patientRequestDto.getEmail());
        }        

        Patient patient = PatientMapper.toPatientModel(patientRequestDto);
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.toPatientResponseDto(savedPatient);        
    }

    @Transactional
    public PatientResponseDto updatePatientById(UUID id, PatientRequestDto patientRequestDto) {
        Patient updatePatient = patientRepository.findById(id)
            .orElseThrow(() -> new PatientNotFoundException("No patient was found with ID: " + id));
            
        if (!updatePatient.getEmail().equalsIgnoreCase(patientRequestDto.getEmail())) {
            if (patientRepository.existsByEmail(patientRequestDto.getEmail())) {
                throw new EmailAlreadyExistsException("Cannot update record. A patient with this email already exists: " + patientRequestDto.getEmail());
            }
        }

        updatePatient.setName(patientRequestDto.getName());
        updatePatient.setEmail(patientRequestDto.getEmail());
        updatePatient.setAddress(patientRequestDto.getAddress());
        updatePatient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        
        return PatientMapper.toPatientResponseDto(updatePatient);
    }
}
