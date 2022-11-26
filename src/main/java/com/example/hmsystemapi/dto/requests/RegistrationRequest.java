package com.example.hmsystemapi.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

import lombok.Setter;

import java.util.Set;
@AllArgsConstructor

@Getter
@Setter
public class RegistrationRequest {
    public  String username;
    public  String fullname;
    public  String email;
    public String password;
    private Set<String> role;
}
