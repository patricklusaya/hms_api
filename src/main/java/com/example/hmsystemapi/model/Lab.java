package com.example.hmsystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter

@NoArgsConstructor
@Table(name = "lab")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Long id;
   
    public String tests;
    public  String postedBy;
    public LocalDate date = LocalDate.now();

    public  String patientId;

    public  String examinedBy;

    public  String results;
    public  String status;
    public Lab(String tests, String postedBy, String patientId) {
        this.tests = tests;
        this.postedBy = postedBy;
        this.date = LocalDate.now();
        this.patientId = patientId;
    }
    public String getStatus() {
        if (results != null && !results.isEmpty()) {
            return "Examined";
        } else {
            return "Not Examined";
        }
    }

    public LocalDate getDate() {
        return date;
    }
}
