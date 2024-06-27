package com.halfacode.location.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class EnvironmentVariableLogger implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }

  /*  @Value("${APPLICATION_NAME}")
    private String applicationName;

    @Value("${OPENCAGE_API_KEY}")
    private String openCageApiKey;

    @Value("${SERVER_PORT}")
    private int serverPort;

    @Value("${RESOURCE_FILE_PATH}")
    private String resourceFilePath;

    @Override
    public void run(String... args) {
        System.out.println("APPLICATION_NAME: " + applicationName);
        System.out.println("OPENCAGE_API_KEY: " + openCageApiKey);
        System.out.println("SERVER_PORT: " + serverPort);
        System.out.println("RESOURCE_FILE_PATH: " + resourceFilePath);
    }*/
}
