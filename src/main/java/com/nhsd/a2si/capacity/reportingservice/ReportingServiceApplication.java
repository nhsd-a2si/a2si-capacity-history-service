package com.nhsd.a2si.capacity.reportingservice;


import com.nhsd.a2si.capacity.reportingservice.api.endpoints.WaitTimes;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReportingServiceApplication {

    @Bean
    ResourceConfig resourceConfig() {
        return new ResourceConfig().register(WaitTimes.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReportingServiceApplication.class, args);
    }
}
