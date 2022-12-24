package com.example.hmsystemapi.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhamPost {
    public  String results;
    public  String patientId;
    public  String sentBy;
}
