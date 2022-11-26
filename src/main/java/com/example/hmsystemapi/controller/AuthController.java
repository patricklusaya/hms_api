package com.example.hmsystemapi.controller;



import com.example.hmsystemapi.dto.requests.LoginRequest;
import com.example.hmsystemapi.dto.requests.RegistrationRequest;
import com.example.hmsystemapi.dto.requests.response.AuthenticationResponse;
import com.example.hmsystemapi.dto.requests.response.MessageResponse;
import com.example.hmsystemapi.repository.EmployeeRepository;
import com.example.hmsystemapi.repository.RoleRepository;
import com.example.hmsystemapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    public EmployeeRepository employeeRepository;
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public AuthService authService;
    @PostMapping("/signup")

    public ResponseEntity<?> registerEmployee( @Valid  @RequestBody RegistrationRequest registrationRequest){
      if (employeeRepository.existsByUsername(registrationRequest.getUsername())){
          return ResponseEntity.badRequest().body(new MessageResponse("Employee with that name already Exists"));
      }
        if (employeeRepository.existsByEmail(registrationRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Email already taken"));
        }
        authService.signup(registrationRequest);

return  ResponseEntity.ok().body(new MessageResponse("Employee registered successfully"));
    }

    @PostMapping("/signin")
    public AuthenticationResponse SignIn(@Valid @RequestBody LoginRequest loginRequest){
        return authService.signin(loginRequest);
    }

}
