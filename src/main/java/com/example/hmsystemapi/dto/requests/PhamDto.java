package com.example.hmsystemapi.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhamDto {
    public  String patientId;
    public  String postedBy;
    public  String prescription;
}
