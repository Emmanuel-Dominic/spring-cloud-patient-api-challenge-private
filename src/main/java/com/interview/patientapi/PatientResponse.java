package com.interview.patientapi;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PatientResponse {
    private List<PatientModel> users;
}
