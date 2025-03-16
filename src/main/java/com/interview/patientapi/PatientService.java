package com.interview.patientapi;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;

@Service
public class PatientService {

    private final String apiUrl = "https://dummyjson.com/users";

    public List<PatientModel> getPatients() {
        try{
            RestTemplate restTemplate = new RestTemplate();
            PatientResponse patientResponse = restTemplate.getForObject(apiUrl, PatientResponse.class);
            return patientResponse != null ? patientResponse.getUsers() : Collections.emptyList();
        }catch (Exception e){
            return null;
        }
    }

    public Page<PatientModel> getPaginatedPatients(int page, int size) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            PatientResponse patientResponse = restTemplate.getForObject(apiUrl, PatientResponse.class);
            List<PatientModel> patients = (patientResponse != null && patientResponse.getUsers() != null)
                    ? patientResponse.getUsers()
                    : Collections.emptyList();

            int start = Math.min(page * size, patients.size());
            int end = Math.min((page + 1) * size, patients.size());

            List<PatientModel> paginatedPatients = patients.subList(start, end);
            Pageable pageable = PageRequest.of(page, size);
            return new PageImpl<>(paginatedPatients, pageable, patients.size());
        } catch (Exception e) {
            return null;
        }
    }
}
