package com.interview.patientapi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PatientService {

    private final String apiUrl = "https://dummyjson.com/users";

    public List<PatientModel> getPatients() {
        try{
            RestTemplate restTemplate = new RestTemplate();
            PatientResponse response = restTemplate.getForObject(apiUrl, PatientResponse.class);
            return response != null ? response.getUsers() : null;
        }catch (Exception e){
            return null;
        }
    }
}
