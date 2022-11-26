package com.example.hmsystemapi.repository;

import com.example.hmsystemapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient , String> {


}
