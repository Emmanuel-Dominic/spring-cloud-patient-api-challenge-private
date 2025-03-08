package com.interview.patientapi;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PatientService {

    private final String apiUrl = "https://dummyjson.com/users";
    private final WebClient webClient;

    public PatientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    public List<PatientModel> getPatients() {
        try {
            Mono<PatientResponse> patientResponse = webClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .bodyToMono(PatientResponse.class);

            return patientResponse.block().getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
