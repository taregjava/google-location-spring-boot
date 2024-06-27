package com.halfacode.location.config;

import com.halfacode.location.controller.RiderController;
import com.halfacode.location.repository.DriverRepository;
import com.halfacode.location.service.DriverService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public DriverRepository driverRepository() {
        return new DriverRepository();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public DriverService driverService() {
        return new DriverService();
    }

    @Bean
    public RiderController riderController() {
        return new RiderController();
    }
}