package com.interview.patientapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientModelTests {

    @Test
    public void testPatientModelGettersAndSetters(){
        PatientModel patientModel = new PatientModel();
        patientModel.setFirstName("Emmanuel");
        patientModel.setLastName("Dominic");
        patientModel.setMaidenName(null);
        patientModel.setAge(30);
        patientModel.setGender("Male");
        patientModel.setMedicalHistory(null);

        Assertions.assertEquals("Emmanuel", patientModel.getFirstName());
        Assertions.assertEquals("Dominic", patientModel.getLastName());
        Assertions.assertEquals("Emmanuel Dominic", patientModel.getName());
        Assertions.assertEquals(30, patientModel.getAge());
        Assertions.assertEquals("Male", patientModel.getGender());
        Assertions.assertNull(patientModel.getMedicalHistory());
    }
}
