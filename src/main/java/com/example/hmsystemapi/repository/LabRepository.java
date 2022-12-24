package com.example.hmsystemapi.repository;

import com.example.hmsystemapi.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Long> {
    boolean existsByPatientId(String patientId);

    List<Lab> findByPatientId(String patientId);

    Lab findByPatientIdAndTests(String patientId, String tests);


//
//   List <Lab> findByPatientId(String patientId);
}
