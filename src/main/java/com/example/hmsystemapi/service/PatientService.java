package com.example.hmsystemapi.service;


import com.example.hmsystemapi.dto.requests.*;
import com.example.hmsystemapi.mapper.LabMapper;
import com.example.hmsystemapi.mapper.LaboratoryMapper;
import com.example.hmsystemapi.model.Lab;
import com.example.hmsystemapi.model.Patient;
import com.example.hmsystemapi.repository.LabRepository;
import com.example.hmsystemapi.repository.PatientRepository;
import com.example.hmsystemapi.repository.PhamRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;

@AllArgsConstructor
@Service
public class PatientService {
    @Autowired
    private final PatientRepository patientRepository;
    @Autowired
    private  final LabRepository labRepository;
    @Autowired
    private  final LabMapper labMapper;
    @Autowired
    private  final PhamRepository phamRepository;
    @Autowired
    private LaboratoryMapper laboratoryMapper;
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

    public void createTest(LabRequest labRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();


        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        String fullname = userDetails.getFullname();
        labRequest.setPostedBy(fullname);
        labRepository.save(labMapper.mapDtoToLab(labRequest));

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)){
//            String doc = authentication.getName();
//            labRequest.setPostedBy(doc);
//            labRepository.save(labMapper.mapDtoToLab(labRequest));
//        }




    }


    public void createResults(LabPost labPost) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) principal;
            String fullname = userDetails.getFullname();
            labPost.setExaminedBy(fullname);
        if (labRepository.existsByPatientId(labPost.getPatientId())){
            labRepository.save(labMapper.mapLabPostDtoToLab(labPost));
        }


        }





    }



    public void createPrescription(PhamDto phamDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) principal;
            String fullname = userDetails.getFullname();
            phamDto.setPostedBy(fullname);
            phamRepository.save(labMapper.mapPhamDtoToPham(phamDto));
//            if (phamRepository.existsByPatientId(phamDto.getPatientId())){
//
//            }


        }
    }
}
