package com.interview.patientapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Page;

import java.util.List;

@SpringBootTest
public class PatientServiceTests {

    private final String apiUrl = "https://dummyjson.com/users";

    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;
    WebClient.RequestHeadersSpec requestHeadersSpec;
    WebClient.ResponseSpec responseSpec;

    @Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    private PatientService patientService;

    @BeforeEach
    public void setup() {
        requestHeadersUriSpec = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        requestHeadersSpec = Mockito.mock(WebClient.RequestHeadersSpec.class);
        responseSpec = Mockito.mock(WebClient.ResponseSpec.class);

        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.uri(apiUrl)).thenReturn(requestHeadersSpec);
        Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);

        Mockito.when(webClientBuilder.baseUrl(apiUrl)).thenReturn(webClientBuilder);
        Mockito.when(webClientBuilder.build()).thenReturn(webClient);

        patientService = new PatientService(webClientBuilder);
    }


    @Test
    public void test_GetPatients_ReturnPatients() {
        String jsonResponse = """
        {
            "users": [
                {
                    "id": 1,
                    "firstName": "Emmanuel",
                    "lastName": "Matembu",
                    "age": 30,
                    "gender": "Male"
                }
            ]
        }
        """;
        PatientModel patientModel = new PatientModel();
        patientModel.setId(1L);
        patientModel.setFirstName("Emmanuel");
        patientModel.setLastName("Matembu");
        patientModel.setAge(30);
        patientModel.setGender("Male");
        patientModel.setMedicalHistory(null);

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setUsers(List.of(patientModel));

        Mockito.when(responseSpec.bodyToMono(PatientResponse.class)).thenReturn(Mono.just(patientResponse));

        List<PatientModel> patients = patientService.getPatients();

        Assertions.assertNotNull(patients);
        Assertions.assertFalse(patients.isEmpty());
        Assertions.assertEquals(1, patients.size());
        Assertions.assertEquals(patientModel, patients.get(0));
    }

    @Test
    public void test_GetPaginatedPatients_ReturnPatients() {
        String jsonResponse = """
        {
            "users": [
                {
                    "id": 1,
                    "firstName": "Emmanuel",
                    "lastName": "Matembu",
                    "age": 30,
                    "gender": "Male"
                }
            ]
        }
        """;
        PatientModel patientModel = new PatientModel();
        patientModel.setId(1L);
        patientModel.setFirstName("Emmanuel");
        patientModel.setLastName("Matembu");
        patientModel.setAge(30);
        patientModel.setGender("Male");
        patientModel.setMedicalHistory(null);

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setUsers(List.of(patientModel));

        Mockito.when(responseSpec.bodyToMono(PatientResponse.class)).thenReturn(Mono.just(patientResponse));

        int page = 0;
        int size = 2;

        Page<PatientModel> paginatedPatients = patientService.getPaginatedPatients(page, size);

        Assertions.assertNotNull(paginatedPatients);
        Assertions.assertFalse(paginatedPatients.isEmpty());
        Assertions.assertEquals(2, paginatedPatients.getSize());
        Assertions.assertEquals(patientModel, paginatedPatients.getContent().get(0));
        Assertions.assertEquals(patientModel.getName(), paginatedPatients.getContent().get(0).getName());
    }
}
