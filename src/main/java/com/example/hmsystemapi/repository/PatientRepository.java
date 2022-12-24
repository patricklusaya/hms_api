package com.example.hmsystemapi.repository;

import com.example.hmsystemapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PatientRepository extends JpaRepository<Patient , String> {


    @Query(value = "SELECT visit_type, COUNT(*) as count FROM patients GROUP BY visit_type", nativeQuery = true)
    List<Object[]> countByVisitType();
}
  

//  @Query(value = "SELECT visitType, COUNT(*) as count FROM Patient GROUP BY visitType")
//  List<Map<String, Object>> countByVisitType();
//
//}


