package com.interview.patientapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTests {

    @Mock
    private PatientService patientService;
    @InjectMocks
    private PatientController patientController;

    @Test
    void testGetPatients_ReturnsOk() {
        PatientModel patient = new PatientModel();
        patient.setId(1L);
        patient.setFirstName("Dominic");
        patient.setLastName("Emmanuel");
        patient.setAge(30);
        patient.setGender("Male");

        when(patientService.getPatients()).thenReturn(List.of(patient));
        ResponseEntity<?> response = patientController.getPatients();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertInstanceOf(List.class, response.getBody());
        Assertions.assertEquals(1, ((List<?>) response.getBody()).size());
    }

    @Test
    void testGetPatients_ReturnsBadRequest() {
        when(patientService.getPatients()).thenReturn(null);
        ResponseEntity<?> response = patientController.getPatients();

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertInstanceOf(Map.class, response.getBody());
        Assertions.assertEquals("Failed to get patients", ((Map<?, ?>) response.getBody()).get("message"));
    }

    @Test
    void testGetPatients_ReturnsNotFound() {
        when(patientService.getPatients()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = patientController.getPatients();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertInstanceOf(Map.class, response.getBody());
        Assertions.assertEquals("No patients found", ((Map<?, ?>) response.getBody()).get("message"));
    }

    @Test
    void testGetPaginatedPatients_ReturnsOk() {
        PatientModel patient = new PatientModel();
        patient.setId(1L);
        patient.setFirstName("Dominic");
        patient.setLastName("Emmanuel");
        patient.setAge(30);
        patient.setGender("Male");

        Page<PatientModel> pagedResponse = new PageImpl<>(List.of(patient));
        when(patientService.getPaginatedPatients(0, 5)).thenReturn(pagedResponse);
        ResponseEntity<?> response = patientController.getPaginatedPatients(0, 5);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertInstanceOf(Page.class, response.getBody());
        Assertions.assertEquals(1, ((Page<?>) response.getBody()).getTotalElements());
    }

    @Test
    void testGetPaginatedPatients_ReturnsBadRequest() {
        when(patientService.getPaginatedPatients(0, 5)).thenReturn(null);
        ResponseEntity<?> response = patientController.getPaginatedPatients(0, 5);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assertions.assertInstanceOf(Map.class, response.getBody());
        Assertions.assertEquals("Failed to get patients", ((Map<?, ?>) response.getBody()).get("message"));
    }
}
