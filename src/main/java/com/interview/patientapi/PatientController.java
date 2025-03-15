package com.interview.patientapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/api/patients")
    public ResponseEntity<?> getPatients() {
        String patients = patientService.getPatients();
        Map<String, String> resp = new HashMap<>();
        if (patients == null) {
            resp.put("message", "No patients found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }else{
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
    }
}
