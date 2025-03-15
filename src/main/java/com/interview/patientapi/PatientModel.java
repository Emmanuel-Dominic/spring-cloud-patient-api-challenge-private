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
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;
    private String medicalHistory;

    public String getName() {
        StringBuilder nameBuilder = new StringBuilder();
        if (firstName != null && !firstName.isEmpty()) {
            nameBuilder.append(firstName).append(" ");
        }
        if (lastName != null && !lastName.isEmpty()) {
            nameBuilder.append(lastName).append(" ");
        }
        if (maidenName != null && !maidenName.isEmpty()) {
            nameBuilder.append(maidenName);
        }
        return nameBuilder.toString().trim();
    }

    public void setName(String firstName, String lastName, String maidenName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.maidenName = maidenName;
    }
}
