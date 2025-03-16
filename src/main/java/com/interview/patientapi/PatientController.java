package com.interview.patientapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/api")
    public ResponseEntity<?> getPatients() {
        List<PatientModel> patients = patientService.getPatients();
        Map<String, String> resp = new HashMap<>();
        if (patients == null) {
            resp.put("message", "Failed to get patients");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }else if(patients.isEmpty()){
            resp.put("message", "No patients found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(patients);
        }
    }

    @GetMapping("/api/patients")
    public ResponseEntity<?> getPaginatedPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<PatientModel> patients = patientService.getPaginatedPatients(page, size);

        if (patients == null) {
            Map<String, String> resp = new HashMap<>();
            resp.put("message", "Failed to get patients");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }else {
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
    }

}
