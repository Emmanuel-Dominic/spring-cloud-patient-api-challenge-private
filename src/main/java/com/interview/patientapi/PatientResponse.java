package com.interview.patientapi;

import java.util.List;

public class PatientResponse {
    private List<PatientModel> users;

    public List<PatientModel> getUsers() {
        return users;
    }

    public void setUsers(List<PatientModel> users) {
        this.users = users;
    }
}
