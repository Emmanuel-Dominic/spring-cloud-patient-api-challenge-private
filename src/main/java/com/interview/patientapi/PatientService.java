package com.interview.patientapi;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Page;

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

    public Page<PatientModel> getPaginatedPatients(int page, int size) {
        try {
            Mono<PatientResponse> patientResponse = webClient.get()
                    .uri(apiUrl)
                    .retrieve()
                    .bodyToMono(PatientResponse.class);
            List<PatientModel> patients = patientResponse.block().getUsers();

            int start = Math.min(page * size, patients.size());
            int end = Math.min((page + 1) * size, patients.size());

            List<PatientModel> paginatedPatients = patients.subList(start, end);
            Pageable pageable = PageRequest.of(page, size);
            return new PageImpl<>(paginatedPatients, pageable, patients.size());
        } catch (Exception e) {
            e.printStackTrace();
            return Page.empty();
        }
    }
}
