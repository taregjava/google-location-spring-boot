package com.halfacode.location.service;

import com.halfacode.location.mapper.OpenCageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeocodingService {

    private final WebClient webClient;

    @Value("${opencage.api.key}")
    private String apiKey;

    public GeocodingService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.opencagedata.com/geocode/v1/json").build();
    }

    public Mono<String> getPlaceName(double latitude, double longitude) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", latitude + "," + longitude)
                        .queryParam("key", apiKey)
                        .queryParam("pretty", 1)
                        .build())
                .retrieve()
                .bodyToMono(OpenCageResponse.class)
                .map(response -> {
                    if (response.getResults() != null && !response.getResults().isEmpty()) {
                        return response.getResults().get(0).getFormatted();
                    } else {
                        return "Unknown place";
                    }
                });
    }

    public Mono<double[]> getCoordinates(String address) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", address)
                        .queryParam("key", apiKey)
                        .queryParam("pretty", 1)
                        .build())
                .retrieve()
                .bodyToMono(OpenCageResponse.class)
                .map(response -> {
                    if (response.getResults() != null && !response.getResults().isEmpty()) {
                        OpenCageResponse.Result result = response.getResults().get(0);
                        return new double[]{result.getGeometry().getLat(), result.getGeometry().getLng()};
                    } else {
                        throw new RuntimeException("Address not found");
                    }
                });
    }
}
