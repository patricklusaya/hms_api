package com.example.hmsystemapi.repository;

import com.example.hmsystemapi.model.Pham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhamRepository extends JpaRepository<Pham , Long> {

    List<Pham> findByPatientId(String patientId);

    boolean existsByPatientId(String patientId);

    Pham findByPatientIdAndPrescription(String patientId, String prescription);
}
