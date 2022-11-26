package com.example.hmsystemapi.repository;


import com.example.hmsystemapi.model.ERole;
import com.example.hmsystemapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
