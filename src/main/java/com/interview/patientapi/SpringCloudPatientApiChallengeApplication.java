package com.interview.patientapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringCloudPatientApiChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudPatientApiChallengeApplication.class, args);
    }
}
