package com.interview.patientapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PatientModelSerializerTests {
    private ObjectMapper objectMapper;
    private PatientModel patient;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(PatientModel.class, new PatientModelSerializer());
        objectMapper.registerModule(module);

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
    void testSerialize_ShouldIncludeOnlySpecifiedFields() throws JsonProcessingException {
        String jsonResult = objectMapper.writeValueAsString(patient);

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
