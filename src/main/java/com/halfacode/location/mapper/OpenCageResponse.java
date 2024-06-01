package com.halfacode.location.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OpenCageResponse {

    @JsonProperty("results")
    private List<Result> results;

    @Data
    public static class Result {
        @JsonProperty("formatted")
        private String formatted;

        @JsonProperty("geometry")
        private Geometry geometry;


    }

    @Data
    public static class Geometry {
        @JsonProperty("lat")
        private double lat;

        @JsonProperty("lng")
        private double lng;


    }
}
