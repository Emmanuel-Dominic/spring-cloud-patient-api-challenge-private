package com.interview.patientapi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PatientService {

    private final String apiUrl = "https://dummyjson.com/users";

    public String getPatients() {
        try{
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, String.class);
        }catch (Exception e){
            return null;
        }
    }
}
