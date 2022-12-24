package com.example.hmsystemapi.dto.requests;

import com.example.hmsystemapi.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
public class LabRequest {

    public  String patientId;
    public  String postedBy;
    public  String tests;





}
