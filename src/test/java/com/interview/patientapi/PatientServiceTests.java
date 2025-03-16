package com.interview.patientapi;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTests {

    private final String apiUrl = "https://dummyjson.com/users";
    private RestTemplate restTemplate;
    private PatientService patientService;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        patientService = new PatientService(restTemplate);
    }

    @Test
    public void testGetPatients_ReturnsJson() {
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
        mockServer.expect(requestTo(apiUrl))
                .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON));

        List<PatientModel> result = patientService.getPatients();
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals("Emmanuel", result.get(0).getFirstName());
        Assertions.assertEquals("Matembu", result.get(0).getLastName());
        mockServer.verify();
    }

    @Test
    void testGetPatients_ReturnsNullOnException() {
        mockServer.expect(requestTo(apiUrl))
                .andRespond(withSuccess("null", MediaType.APPLICATION_JSON));
        List<PatientModel> result = patientService.getPatients();
        Assertions.assertNotNull(result);
        mockServer.verify();
    }
}
