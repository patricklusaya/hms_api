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
    private Long id;
    private String username;
    private String email;
    private String token;
    private  String fullname;
    private String type ="Bearer";
    private List<String> roles;


    public AuthenticationResponse(String jwt, Long id, String email, String username,String fullname , List<String> roles) {
        this.id = id;
        this.token = jwt;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.roles = roles;
    }
}
