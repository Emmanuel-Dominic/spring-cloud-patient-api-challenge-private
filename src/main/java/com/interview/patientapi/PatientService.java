package com.interview.patientapi;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class PatientService {

    private final String apiUrl = "http://dummyjson.com/users";
    private final WebClient webClient;

    public PatientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    public List<PatientModel> getPatients() {
        try{
            PatientResponse patientResponse = webClient.get()
                    .uri(apiUrl)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve().bodyToMono(PatientResponse.class).block();
            return patientResponse != null ? patientResponse.getUsers() : null;
        }catch (Exception e){
            return null;
        }
    }
}
