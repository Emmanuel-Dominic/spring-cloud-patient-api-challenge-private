package com.interview.patientapi;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientResponseTests {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDeserialize() throws Exception {
        String json = """
                {
                    "users": [
                        {
                          "id": 1,
                          "name": "Emmanuel Dominic",
                          "age": 30,
                          "gender": "Male",
                          "medicalHistory": null
                        }
                    ]
                }
                """;
        PatientResponse patientResponse = objectMapper.readValue(json, PatientResponse.class);

        Assertions.assertEquals(1, patientResponse.getUsers().size());
        Assertions.assertEquals(1, patientResponse.getUsers().get(0).getId());
        Assertions.assertEquals(30, patientResponse.getUsers().get(0).getAge());
        Assertions.assertEquals("Male", patientResponse.getUsers().get(0).getGender());
        Assertions.assertNull(patientResponse.getUsers().get(0).getMedicalHistory());
    }
}
