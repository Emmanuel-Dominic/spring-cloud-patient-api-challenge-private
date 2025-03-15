package com.interview.patientapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class PatientModel {
    private Long id;
    private String name;
    private int age;
    private String gender;
    private String medicalHistory;
}
