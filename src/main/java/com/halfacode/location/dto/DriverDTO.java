package com.halfacode.location.dto;

public class DriverDTO {
    private String name;
    private String vehicle;
    private boolean available;
    private double latitude;
    private double longitude;

    public DriverDTO() {
    }

    public DriverDTO(String name, String vehicle, boolean available, double latitude, double longitude) {
        this.name = name;
        this.vehicle = vehicle;
        this.available = available;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}