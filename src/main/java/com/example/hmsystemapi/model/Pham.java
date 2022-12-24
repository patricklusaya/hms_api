package com.example.hmsystemapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter

@NoArgsConstructor
@Table(name = "pham")
public class Pham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  Long id;

    public String prescription;
    public  String postedBy;
    public LocalDate date = LocalDate.now();

    public  String patientId;

    public  String sentBy;

    public  String results;
    public  String status;

    public Pham(String prescription, String postedBy, String patientId ) {
        this.prescription = prescription;
        this.postedBy = postedBy;
        this.date = LocalDate.now();
        this.patientId = patientId;

//        this.status = this.results == null || this.results.isEmpty() ? "Not dispensed" : "Dispensed";

    }
    public String getStatus() {
        if (results != null && !results.isEmpty()) {
            return "Dispensed";
        } else {
            return "Not dispensed";
        }
    }

    public LocalDate getDate() {
        return date;
    }

}
