package com.example.hmsystemapi.service;



import com.example.hmsystemapi.dto.requests.LoginRequest;
import com.example.hmsystemapi.dto.requests.RegistrationRequest;
import com.example.hmsystemapi.dto.requests.response.AuthenticationResponse;
import com.example.hmsystemapi.model.ERole;
import com.example.hmsystemapi.model.Employee;
import com.example.hmsystemapi.model.Role;
import com.example.hmsystemapi.repository.EmployeeRepository;
import com.example.hmsystemapi.repository.RoleRepository;
import com.example.hmsystemapi.security.jwt.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {
    public PasswordEncoder encoder;
    public RoleRepository roleRepository;
    public EmployeeRepository employeeRepository;

    @Autowired
    private  final AuthenticationManager authenticationManager;

    @Autowired
    private final JwtUtils jwtUtils;
    public void signup(RegistrationRequest registrationRequest) {
       Employee employee = new Employee();
       employee.setEmail(registrationRequest.getEmail());
       employee.setUsername(registrationRequest.getUsername());
       employee.setFullname(registrationRequest.getFullname());

       employee.setPassword(encoder.encode(registrationRequest.getPassword()));

        Set<String> strRoles = registrationRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role receptionRole = roleRepository.findByName(ERole.ROLE_RECEPTIONIST)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(receptionRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role admin is not found."));
                        roles.add(adminRole);

                        break;


                    case "doc":
                        Role docRole = roleRepository.findByName(ERole.ROLE_DOCTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role doctor is not found."));
                        roles.add(docRole);

                        break;
                    case "lab":
                        Role labRole = roleRepository.findByName(ERole.ROLE_LAB)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(labRole);

                        break;
                    case "pham":
                        Role phamRole = roleRepository.findByName(ERole.ROLE_PHARMACY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(phamRole);

                        break;
                    case "nurse":
                        Role nurseRole = roleRepository.findByName(ERole.ROLE_NURSE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(nurseRole);

                        break;
                    default:
                        Role receptionRole = roleRepository.findByName(ERole.ROLE_RECEPTIONIST)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(receptionRole);
                }
            });
        }
        employee.setRoles(roles);
        employeeRepository.save(employee);

    }

    public AuthenticationResponse signin(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return new AuthenticationResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getFullname(),
                roles);
    }
    }

