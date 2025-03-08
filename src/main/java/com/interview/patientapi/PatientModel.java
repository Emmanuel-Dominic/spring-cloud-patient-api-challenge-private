package com.interview.patientapi;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class PatientModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private String name;
    private int age;
    private String gender;
    private String medicalHistory;

    public String getName() {
        return String.format("%s %s %s", firstName, lastName, maidenName).trim();
    }

    public void setName(String firstName, String lastName, String maidenName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
        this.name = String.format("%s %s %s", firstName, lastName, maidenName).trim();
    }
}
