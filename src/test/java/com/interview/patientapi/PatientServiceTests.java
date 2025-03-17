package com.interview.patientapi;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTests {

    private final String apiUrl = "https://dummyjson.com/users";
    private PatientService patientService;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setup() {
        RestTemplate restTemplate = new RestTemplate();
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
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Emmanuel", result.get(0).getFirstName());
        Assertions.assertEquals("Matembu", result.get(0).getLastName());
        mockServer.verify();
    }

    @Test
    void testGetPatients_ReturnsNullOnException() {
        mockServer.expect(requestTo(apiUrl))
                .andRespond(withSuccess("{\n \"users\": [] \n}", MediaType.APPLICATION_JSON));
        List<PatientModel> result = patientService.getPatients();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(0, result.size());
        mockServer.verify();
    }

    @Test
    public void testGetPaginatedPatients_ReturnsJson() {
        String jsonResponse = """
        {
            "users": [
                {
                    "id": 1,
                    "firstName": "Emmanuel",
                    "lastName": "Matembu",
                    "age": 30,
                    "gender": "Male",
                    "medicalHistory": null
                }
            ],
            "pageable": {
                "pageNumber": 0,
                "pageSize": 1,
                "sort": {
                     "empty": true,
                     "sorted": false,
                     "unsorted": true
                },
                "offset": 0,
                "paged": true,
                "unpaged": false
            },
            "last": false,
            "totalPages": 1,
            "totalElements": 1,
            "first": true,
            "size": 5,
            "number": 0,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "numberOfElements": 1,
            "empty": false
        }
        """;
        mockServer.expect(requestTo(apiUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON));
        Page<PatientModel> result = patientService.getPaginatedPatients(0, 5);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getNumberOfElements());
        Assertions.assertEquals(1, result.getContent().size());
        Assertions.assertEquals(5, result.getSize());
        Assertions.assertEquals("Emmanuel", result.getContent().get(0).getFirstName());
        Assertions.assertEquals("Matembu", result.getContent().get(0).getLastName());
        mockServer.verify();
    }

    @Test
    void testGetPaginatedPatients_ReturnsNullOnException() {
        String emptyResponse = """
        {
            "users": [],
            "pageable": {
                "pageNumber": 0,
                "pageSize": 5,
                "sort": {
                    "empty": true,
                    "sorted": false,
                    "unsorted": true
                },
                "offset": 0,
                "paged": true,
                "unpaged": false
            },
            "last": true,
            "totalPages": 0,
            "totalElements": 0,
            "first": true,
            "size": 5,
            "number": 0,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "numberOfElements": 0,
            "empty": true
        }
        """;
        mockServer.expect(requestTo(apiUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(emptyResponse, MediaType.APPLICATION_JSON));
        Page<PatientModel> result = patientService.getPaginatedPatients(0, 5);

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getContent().isEmpty());
        Assertions.assertEquals(0, result.getTotalElements());
        Assertions.assertEquals(0, result.getNumberOfElements());
        Assertions.assertEquals(0, result.getContent().size());
        mockServer.verify();
    }
}
