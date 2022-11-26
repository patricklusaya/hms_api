package com.example.hmsystemapi.controller;


import com.example.hmsystemapi.dto.requests.LabRequest;
import com.example.hmsystemapi.dto.requests.PatientRegRequest;
import com.example.hmsystemapi.dto.requests.response.MessageResponse;
import com.example.hmsystemapi.model.Patient;
import com.example.hmsystemapi.repository.LabRepository;
import com.example.hmsystemapi.repository.PatientRepository;
import com.example.hmsystemapi.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
public class TestController {
    @Autowired
    private  final PatientService patientService;
    @Autowired
    private  final LabRepository labRepository;
    @Autowired
    private  final PatientRepository patientRepository;
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/patients")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_ADMIN')")
    public List<Patient> docRecAdminAccess() {
        return  patientRepository.findAll();
    }
    @GetMapping("/patients/{id}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_ADMIN')")
    public Optional<Patient> findPatientById(@PathVariable("id") String id ){
        return patientRepository.findById(id);
    }

    @PostMapping("/register-patient")
    @PreAuthorize("hasRole('ROLE_RECEPTIONIST')")
    public ResponseEntity<MessageResponse> registerPatient(@RequestBody PatientRegRequest patientRegRequest) {

        patientService.registerPatient(patientRegRequest);
        return ResponseEntity.ok().body(new MessageResponse("Patient has been registered"));
    }

    @PostMapping("/lab_request")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public ResponseEntity<MessageResponse> createDiagnosis(@RequestBody LabRequest labRequest)

    {
        String pid = labRequest.getPatientId();
        if (patientRepository.existsById(labRequest.getPatientId()))
        {
            patientService.createDiagnosis(labRequest);
            return  ResponseEntity.ok().body(new MessageResponse("Test sent successfully"));
        }
        else{
            return  ResponseEntity.badRequest().body(
                    new MessageResponse("Id entered does not exist ")
            );
        }

    }

    @GetMapping("/pharmacy")
    @PreAuthorize("hasRole('ROLE_PHARMACY')")
    public String moderatorAccess() {
        return "Pharmacy.";
    }

    @GetMapping("/lab")
    @PreAuthorize("hasRole('ROLE_LAB')")
    public String adminAccess() {
        return "Lab patients";
}}
