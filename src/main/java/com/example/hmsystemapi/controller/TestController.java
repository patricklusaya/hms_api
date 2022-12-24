package com.example.hmsystemapi.controller;


import com.example.hmsystemapi.dto.requests.*;
import com.example.hmsystemapi.dto.requests.response.MessageResponse;
import com.example.hmsystemapi.model.Lab;
import com.example.hmsystemapi.model.Patient;
import com.example.hmsystemapi.model.Pham;
import com.example.hmsystemapi.repository.LabRepository;
import com.example.hmsystemapi.repository.PatientRepository;
import com.example.hmsystemapi.repository.PhamRepository;
import com.example.hmsystemapi.service.PatientService;
import com.example.hmsystemapi.service.UserDetailsImpl;
import com.example.hmsystemapi.util.ReportUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
@AllArgsConstructor
public class TestController {
    @Autowired
    private  final PatientService patientService ;
    @Autowired
    private  final LabRepository labRepository;
    @Autowired
    private  final PatientRepository patientRepository;
    @Autowired
    private  final PhamRepository phamRepository;

    @Autowired
    ReportUtil reportUtil;
    @Autowired
    ServletContext sc;

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



@PostMapping("/lab-request")
@PreAuthorize("hasRole('ROLE_DOCTOR')")
public  ResponseEntity<MessageResponse > createTest (@RequestBody LabRequest labRequest){
        patientService.createTest(labRequest);
        return  ResponseEntity.ok().body(new MessageResponse( "Tests sent successfully"));
}
 @GetMapping("/lab-requests")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_LAB')")
    public List<Lab> getAllLabRequests() {
        return  labRepository.findAll();
    }

    @GetMapping("/pham-requests")
    @PreAuthorize("hasRole('ROLE_PHARMACY') ")
    public List<Pham> getAllPhamRequests() {
        return  phamRepository.findAll();
    }

 @GetMapping("/lab-results/{patientId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_RECEPTIONIST') or hasRole('ROLE_LAB')")
    public List <Lab> findPatientByPatientId(@PathVariable("patientId") String patientId ){
         return labRepository.findByPatientId(patientId);
    }

    @GetMapping("/pres-results/{patientId}")
    @PreAuthorize("hasRole('ROLE_DOCTOR') or hasRole('ROLE_PHARMACY') or hasRole('ROLE_LAB')")
    public List <Pham> getPatientByPatientId(@PathVariable("patientId") String patientId ){
        return phamRepository.findByPatientId(patientId);
    }

@PutMapping("/create-results")
@PreAuthorize("hasRole('ROLE_LAB')")
public  ResponseEntity <MessageResponse> createResults ( @RequestBody LabPost labPost){
    patientService.createResults(labPost);
    return ResponseEntity.ok().body(new MessageResponse( "Results sent "));

}


    @PostMapping("/create-prescription")
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    public  ResponseEntity <MessageResponse> createPrescription(@RequestBody PhamDto phamDto){
        patientService.createPrescription(phamDto);
//        Pham pham = new Pham();
//        pham.setPrescription(phamDto.getPrescription());
//        pham.setPostedBy("Gembe");
//        phamRepository.save(pham);

        return ResponseEntity.ok().body(new MessageResponse( "Prescription sent "));

    }

    @PutMapping("/create-results/{patientId}/{tests}")
    @PreAuthorize("hasRole('ROLE_LAB')")
    public ResponseEntity<Lab> updateLab(@PathVariable("patientId") String patientId,
                                         @PathVariable("tests") String tests,
                                         @RequestBody Lab lab) {


        Lab mylab = labRepository.findByPatientIdAndTests( patientId , tests);
        mylab.setResults(lab.getResults());


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();


            UserDetailsImpl userDetails = (UserDetailsImpl) principal;
            String fullname = userDetails.getFullname();
            mylab.setExaminedBy(fullname);
            Lab updatedLab = labRepository.save(mylab);


            return new ResponseEntity<>(updatedLab, HttpStatus.OK);




    }

    @PutMapping("/create-prescription-results/{patientId}/{prescription}")
    @PreAuthorize("hasRole('ROLE_PHARMACY')")
    public ResponseEntity<Pham> updatePres(@PathVariable("patientId") String patientId,
                                         @PathVariable("prescription") String prescription,
                                         @RequestBody Pham pham) {


        Pham myPham = phamRepository.findByPatientIdAndPrescription( patientId , prescription);
        myPham.setResults(pham.getResults());


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();


        UserDetailsImpl userDetails = (UserDetailsImpl) principal;
        String fullname = userDetails.getFullname();
        myPham.setSentBy(fullname);
        Pham updatedPres= phamRepository.save(myPham);


        return new ResponseEntity<>(updatedPres, HttpStatus.OK);




    }

    @GetMapping("/piechart")
    public Map<String, BigInteger> countByVisitType() {
        Map<String, BigInteger> result = new HashMap<>();
        List<Object[]> counts = patientRepository.countByVisitType();
        for (Object[] row : counts) {
            result.put((String) row[0], (BigInteger) row[1]);
        }
        return result;
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
