package com.interview.patientapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/api/patients")
    public ResponseEntity<?> getPatients() {
        List<PatientModel> patients = patientService.getPatients();
        System.out.println("Patients: " + patients);

        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No patients found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }
}
