package com.example.hmsystemapi.repository;

import com.example.hmsystemapi.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRepository extends JpaRepository<Lab, Long> {
    boolean existsByPatientId(String patientId);
}
