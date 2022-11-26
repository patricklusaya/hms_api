package com.example.hmsystemapi.service;


import com.example.hmsystemapi.dto.requests.LabRequest;
import com.example.hmsystemapi.dto.requests.PatientRegRequest;
import com.example.hmsystemapi.model.Lab;
import com.example.hmsystemapi.model.Patient;
import com.example.hmsystemapi.repository.LabRepository;
import com.example.hmsystemapi.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@AllArgsConstructor
@Service
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;
    @Autowired
    private  final LabRepository labRepository;
    public void registerPatient(PatientRegRequest patientRegRequest) {
        Patient patient = new Patient();
      patient.setFullname(patientRegRequest.getFullname());
      patient.setAge(patientRegRequest.getAge());
      patient.setTribe(patientRegRequest.getTribe());
        patient.setAddress(patientRegRequest.getAddress());
        patient.setBillCategory(patientRegRequest.getBillCategory());
        patient.setInsuranceNo(patientRegRequest.getInsuranceNo());
        patient.setInsuranceType(patientRegRequest.getInsuranceType());
        patient.setVisitName(patientRegRequest.getVisitName());
        patient.setVisitType(patientRegRequest.getVisitType());

      patientRepository.save(patient);
    }


    public void createDiagnosis(LabRequest labRequest) {

        Principal principal = null;
        Lab lab = new Lab();
        lab.setTests(labRequest.getTests());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String doc = authentication.getName();

            lab.setPostedBy(doc);
        }
        lab.setDate(lab.getDate());
        lab.setPatientId(labRequest.getPatientId());
        labRepository.save(lab);



    }
}
