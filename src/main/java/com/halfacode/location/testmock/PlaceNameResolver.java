package com.halfacode.location.testmock;


import java.io.IOException;

public class PlaceNameResolver {

    public static String getPlaceName(double latitude, double longitude) throws IOException {
    /*    JOpenCageGeocoder geocoder = new JOpenCageGeocoder(API_KEY);
        JOpenCageReverseRequest request = new JOpenCageReverseRequest(latitude, longitude);
        request.setLanguage("en"); // Set the language for the response
        JOpenCageResponse response = geocoder.reverse(request);

        // Check if the response is successful
        if (response != null && response.getStatus().getCode() == 200) {
            // Get the first result
            return response.getFirstFormatted();
        } else {
            // Handle error
            System.out.println("Error: " + response.getStatus().getMessage());
            return null;
        }*/
        return null;
    }
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Radius of the Earth in kilometers
        final double R = 6371.0;

        // Convert latitude and longitude from degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences between coordinates
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;

        // Calculate the distance using the Haversine formula
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return distance;
    }
    public static void main(String[] args) {
        // Example coordinates for two places
        double lat1 = 36.40953689; // Latitude of Place 1
        double lon1 = 15.4157414; // Longitude of Place 1
        double lat2 = 35.598333; // Latitude of Place 2
        double lon2 = 15.33; // Longitude of Place 2

        // Calculate the distance between the two places
        double distance = calculateDistance(lat1, lon1, lat2, lon2);
        System.out.println("Distance between the two places: " + distance + " kilometers");
    }
  /*  public static void main(String[] args) throws IOException {
        // Example coordinates for the Mediterranean Sea
        double latitude = 35.0;
        double longitude = 18.0;

        // Get the name of the place
        String placeName = getPlaceName(latitude, longitude);
        System.out.println("Place Name: " + placeName);
    }*/
}