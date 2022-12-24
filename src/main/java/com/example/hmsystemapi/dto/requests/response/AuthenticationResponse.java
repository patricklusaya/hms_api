package com.example.hmsystemapi.dto.requests.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public  class AuthenticationResponse {
    private String id;
    private String username;
    private String email;
    private String token;
    private  String fullname;
    public  String department;
    private String type ="Bearer";
    private List<String> roles;


    public AuthenticationResponse(String jwt, String id, String email, String username,String fullname ,String department,  List<String> roles) {

        this.token = jwt;
        this.id = id;
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.department  = department;
        this.roles = roles;
    }



}
