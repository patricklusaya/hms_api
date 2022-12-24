package com.example.hmsystemapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(	name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Employee {
    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @GenericGenerator(
            name = "emp_seq",
            strategy = "com.example.hmsystemapi.model.StringPrefixedSequenceIdGenerator",
            parameters = {

                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "HMS_EMPLOYEE_"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")
            })
    public String id;
    @NotBlank
//    this is the employee id on the front end
    public String username;
    @NotBlank
    public  String email;
    @NotBlank
    public  String department;
    @NotBlank
    public String password;
    @NotBlank
    public String fullname;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "employee_roles",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> roles = new HashSet<>();
}
