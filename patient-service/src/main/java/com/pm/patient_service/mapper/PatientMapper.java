package com.pm.patient_service.mapper;

import java.time.LocalDate;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.model.Patient;

public class PatientMapper {
    public static PatientResponseDto toPatientResponseDto(Patient patient) {
        PatientResponseDto responseDto = new PatientResponseDto();
        responseDto.setId(patient.getId().toString());
        responseDto.setName(patient.getName());
        responseDto.setEmail(patient.getEmail());
        responseDto.setAddress(patient.getAddress());
        responseDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return responseDto;
    }

    public static Patient toPatientModel(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient();
        patient.setName(patientRequestDto.getName());
        patient.setEmail(patientRequestDto.getEmail());
        patient.setAddress(patientRequestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDto.getRegisteredDate()));
        return patient;
    }    
}
