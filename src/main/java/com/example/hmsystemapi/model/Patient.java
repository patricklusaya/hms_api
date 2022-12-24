package com.example.hmsystemapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @GenericGenerator(
            name = "patient_seq",
            strategy = "com.example.hmsystemapi.model.StringPrefixedSequenceIdGenerator",
            parameters = {

                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "HMS_PATIENT_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })

    public  String id;
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
