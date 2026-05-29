package com.pm.patient_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pm.patient_service.dto.PatientRequestDto;
import com.pm.patient_service.dto.PatientResponseDto;
import com.pm.patient_service.dto.validator.CreatePatientValidationGroup;
import com.pm.patient_service.service.PatientService;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatientsRequest() {
        List<PatientResponseDto> allPatientsResponseDtos = patientService.getAllPatientsFromRepository();
        return ResponseEntity.ok().body(allPatientsResponseDtos);   
    }
    
    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatientRequest(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto savedPatientResponseDto = patientService.savePatientToRepository(patientRequestDto);
        return ResponseEntity.ok().body(savedPatientResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatientRequest(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto) {
        PatientResponseDto updatedPatientResponseDto = patientService.updatePatientById(id, patientRequestDto);
        return ResponseEntity.ok().body(updatedPatientResponseDto);
    }
}
