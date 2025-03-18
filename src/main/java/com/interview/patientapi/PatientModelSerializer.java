package com.interview.patientapi;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PatientModelSerializer extends StdSerializer<PatientModel> {

    public PatientModelSerializer() {
        super(PatientModel.class);
    }

    @Override
    public void serialize(PatientModel patientModel, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", patientModel.getId());
        jsonGenerator.writeStringField("name", patientModel.getName());  // Only 'name' should be serialized
        jsonGenerator.writeNumberField("age", patientModel.getAge());
        jsonGenerator.writeStringField("gender", patientModel.getGender());
        jsonGenerator.writeStringField("medicalHistory", patientModel.getMedicalHistory());
        jsonGenerator.writeEndObject();
    }
}
