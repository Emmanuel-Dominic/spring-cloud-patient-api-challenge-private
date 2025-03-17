package com.interview.patientapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PatientModelSerializerTests {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerialize() throws Exception {
        PatientModel patientModel = new PatientModel();
        patientModel.setId(1L);
        patientModel.setFirstName("Emmanuel");
        patientModel.setLastName("Dominic");
        patientModel.setAge(30);
        patientModel.setGender("Male");
        patientModel.setMedicalHistory(null);
        String expectedJson = "{\"id\":1,\"name\":\"Emmanuel Dominic\",\"age\":30,\"gender\":\"Male\",\"medicalHistory\":null}";

        String actualJson = objectMapper.writeValueAsString(patientModel);
        Assertions.assertEquals(expectedJson, actualJson);
    }
}
