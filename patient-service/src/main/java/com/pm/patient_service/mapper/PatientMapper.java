package com.pm.patient_service.mapper;

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
}
