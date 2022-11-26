package com.example.hmsystemapi.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    public  String username;
    @NotBlank
    public  String password;
}
