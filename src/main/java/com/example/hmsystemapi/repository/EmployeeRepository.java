package com.example.hmsystemapi.repository;


import com.example.hmsystemapi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);

 

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
