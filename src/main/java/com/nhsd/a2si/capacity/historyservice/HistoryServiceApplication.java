package com.nhsd.a2si.capacity.historyservice;


import com.nhsd.a2si.capacity.historyservice.api.WaitTimes;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HistoryServiceApplication {

    @Bean
    ResourceConfig resourceConfig() {
        return new ResourceConfig().register(WaitTimes.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HistoryServiceApplication.class, args);
    }
}
