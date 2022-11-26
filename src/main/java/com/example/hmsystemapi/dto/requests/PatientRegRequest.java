package com.example.hmsystemapi.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatientRegRequest {
    public  String fullname;
    public String tribe;
    public String address;
    public String visitType;
    public String visitName;
    public String billCategory;
    public String insuranceType;
    public String insuranceNo;
    private String age;
}
