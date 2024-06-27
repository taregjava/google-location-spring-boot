package com.halfacode.location;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocationAttitudeLongitudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationAttitudeLongitudeApplication.class, args);
	}

	/*@PostConstruct
	public void init() {
		System.out.println("APPLICATION_NAME: " + System.getenv("APPLICATION_NAME"));
		System.out.println("OPENCAGE_API_KEY: " + System.getenv("OPENCAGE_API_KEY"));
		System.out.println("SERVER_PORT: " + System.getenv("SERVER_PORT"));
		System.out.println("RESOURCE_FILE_PATH: " + System.getenv("RESOURCE_FILE_PATH"));
	}*/

}
