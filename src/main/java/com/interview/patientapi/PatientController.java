package com.interview.patientapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/api/")
    public ResponseEntity<?> getPatients() {
        List<PatientModel> patients = patientService.getPatients();
        System.out.println("Patients: " + patients);

        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "No patients found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(patients);
    }

    @GetMapping("/api/patients")
    public ResponseEntity<Page<PatientModel>> getPaginatedPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Page<PatientModel> patients = patientService.getPaginatedPatients(page, size);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
