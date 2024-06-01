package com.halfacode.location.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.halfacode.location.model.Location;
import com.halfacode.location.util.DistanceCalculator;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class LocationService {

    private final WebClient webClient;

    private Map<Integer, Location> locations = new HashMap<>();

    @Value("${opencage.api.key}")
    private String opencageApiKey;

    public LocationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.opencagedata.com").build();
    }

    @PostConstruct
    public void init() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/GZ_GPS.txt");
            if (inputStream == null) {
                throw new IllegalArgumentException("Resource file not found: /GZ_GPS.txt");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                // Trim and handle potential BOM characters
                int id = Integer.parseInt(parts[0].trim().replace("\uFEFF", ""));
                double latitude = Double.parseDouble(parts[1].trim());
                double longitude = Double.parseDouble(parts[2].trim());
                locations.put(id, new Location(id, latitude, longitude));
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize LocationService", e);
        }
    }

    public Location getLocationById(int id) {
        return locations.get(id);
    }

    public String getPlaceName(double latitude, double longitude) {
        String url = String.format("/geocode/v1/json?key=%s&q=%s,%s", opencageApiKey, latitude, longitude);

        String responseBody = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (responseBody != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseJson = objectMapper.readTree(responseBody);
                JsonNode results = responseJson.get("results").get(0);
                return results.get("formatted").asText();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "Unknown";
    }

    public double getDistanceBetweenLocations(double lat1, double lon1, double lat2, double lon2) {
        return DistanceCalculator.calculateDistance(lat1, lon1, lat2, lon2);
    }
}