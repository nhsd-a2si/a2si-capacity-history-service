package com.nhsd.a2si.capacity.reportingservice;


import com.nhsd.a2si.capacity.reportingservice.api.endpoints.Logs;
import com.nhsd.a2si.capacity.reportingservice.api.endpoints.WaitTimes;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReportingServiceApplication {

    @Bean
    ResourceConfig resourceConfig() {
        // If you need to add a new endpoint, include it here so that it is added at boot.
        return new ResourceConfig()
                .register(WaitTimes.class)
                .register(Logs.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReportingServiceApplication.class, args);
    }
}
