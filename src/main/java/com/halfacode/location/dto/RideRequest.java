package com.halfacode.location.dto;

import lombok.Data;

@Data
public class RideRequest {
    private double pickupLatitude;
    private double pickupLongitude;
    private double dropoffLatitude;
    private double dropoffLongitude;
}
