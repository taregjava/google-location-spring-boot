package com.halfacode.location.controller;

import com.halfacode.location.model.Location;
import com.halfacode.location.service.GeocodingService;
import com.halfacode.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private GeocodingService geocodingService;

    @GetMapping("/api/locations/place")
    public Mono<String> getPlaceName(@RequestParam double latitude, @RequestParam double longitude) {
        return Mono.just(locationService.getPlaceName(latitude, longitude));
    }

    @GetMapping("/PlaceName")
    public Mono<String> getPlaceName2(@RequestParam double latitude, @RequestParam double longitude) {
        return geocodingService.getPlaceName(latitude, longitude);
    }

    @GetMapping("/api/locations")
    public ResponseEntity<Location> getLocationById(@RequestParam int id) {
        Location location = locationService.getLocationById(id);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/distance")
    public ResponseEntity<Double> getDistance(@RequestParam double lat1, @RequestParam double lon1,
                                              @RequestParam double lat2, @RequestParam double lon2) {
        double distance = locationService.getDistanceBetweenLocations(lat1, lon1, lat2, lon2);
        return new ResponseEntity<>(distance, HttpStatus.OK);
    }

    @GetMapping("/distanceBetweenAddresses")
    public Mono<ResponseEntity<Double>> getDistanceBetweenAddresses(@RequestParam String address1, @RequestParam String address2) {
        return geocodingService.getCoordinates(address1)
                .zipWith(geocodingService.getCoordinates(address2))
                .map(tuple -> {
                    double[] coords1 = tuple.getT1();
                    double[] coords2 = tuple.getT2();
                    double distance = locationService.getDistanceBetweenLocations(coords1[0], coords1[1], coords2[0], coords2[1]);
                    return new ResponseEntity<>(distance, HttpStatus.OK);
                })
                .onErrorResume(e -> Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST)));
    }

    @GetMapping("/distanceBetweenUrls")
    public Mono<ResponseEntity<Double>> getDistanceBetweenUrls(@RequestParam String url1, @RequestParam String url2) {
        double[] coords1 = extractCoordinatesFromUrl(url1);
        double[] coords2 = extractCoordinatesFromUrl(url2);

        if (coords1 == null || coords2 == null) {
            return Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        }

        double distance = locationService.getDistanceBetweenLocations(coords1[0], coords1[1], coords2[0], coords2[1]);
        return Mono.just(new ResponseEntity<>(distance, HttpStatus.OK));
    }

    private double[] extractCoordinatesFromUrl(String url) {
        Pattern pattern = Pattern.compile("@([0-9.]+),([0-9.]+)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            double latitude = Double.parseDouble(matcher.group(1));
            double longitude = Double.parseDouble(matcher.group(2));
            return new double[]{latitude, longitude};
        }
        return null;
    }
}
