package com.interview.patientapi;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class PatientModelDeserializer extends StdDeserializer<PatientModel> {

    public PatientModelDeserializer() {
        super(PatientModel.class);
    }

    @Override
    public PatientModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        JsonNode nameNode = jsonNode.get("name");
        PatientModel patientModel = new PatientModel();

        if (nameNode != null && !nameNode.asText().isEmpty()) {
            String[] names = nameNode.asText().split(" ");

            patientModel.setFirstName(names[0]);

            if (names.length > 1) {
                patientModel.setLastName(names[1]);
            }
            if (names.length > 2) {
                patientModel.setMaidenName(names[2]);
            }
        }
        patientModel.setId(jsonNode.get("id").asLong());
        patientModel.setAge(jsonNode.get("age").asInt());
        patientModel.setGender(jsonNode.get("gender").asText());
        JsonNode medicalHistoryNode = jsonNode.get("medicalHistory");
        patientModel.setMedicalHistory(medicalHistoryNode.isNull() ? null : medicalHistoryNode.asText());
        return patientModel;
    }
}
