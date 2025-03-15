package com.interview.patientapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatientModelTests {
    private PatientModel patient;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        patient = new PatientModel();
        patient.setId(1L);
        patient.setFirstName("Emmanuel");
        patient.setLastName("Matembu");
        patient.setMaidenName("N/A");
        patient.setAge(30);
        patient.setGender("Male");
        patient.setMedicalHistory("No known issues");
    }

    @Test
    void testGetName_ShouldReturnFullName() {
        Assertions.assertEquals("Emmanuel Matembu N/A", patient.getName());
    }

    @Test
    void testGetName_WhenNoMaidenName_ShouldReturnFirstLastName() {
        patient.setMaidenName(null);
        Assertions.assertEquals("Emmanuel Matembu", patient.getName());
    }

    @Test
    void testSerialization_ShouldConvertToJson() throws JsonProcessingException {
        String jsonResult = objectMapper.writeValueAsString(patient);
        String jsonExpected = "{\"id\":1,\"name\":\"Emmanuel Matembu N/A\",\"age\":30,\"gender\":\"Male\",\"medicalHistory\":\"No known issues\"}";
        Assertions.assertEquals(jsonExpected, jsonResult);
        Assertions.assertTrue(jsonResult.contains("\"id\":1"));
        Assertions.assertTrue(jsonResult.contains("\"name\":\"Emmanuel Matembu N/A\""));
        Assertions.assertTrue(jsonResult.contains("\"age\":30"));
        Assertions.assertTrue(jsonResult.contains("\"gender\":\"Male\""));
        Assertions.assertTrue(jsonResult.contains("\"medicalHistory\":\"No known issues\""));

        Assertions.assertFalse(jsonResult.contains("firstName"));
        Assertions.assertFalse(jsonResult.contains("lastName"));
        Assertions.assertFalse(jsonResult.contains("maidenName"));
    }
}
