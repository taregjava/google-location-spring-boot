package com.halfacode.location.service;

import com.halfacode.location.dto.DriverDTO;
import com.halfacode.location.dto.DriverResponse;
import com.halfacode.location.dto.RideRequest;
import com.halfacode.location.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private LocationService locationService;

    public DriverResponse findAvailableDrivers(RideRequest rideRequest) {
        List<DriverDTO> availableDrivers = driverRepository.findAll().stream()
                .filter(DriverDTO::isAvailable)
                .filter(driver -> {
                    double distance = locationService.getDistanceBetweenLocations(
                            rideRequest.getPickupLatitude(), rideRequest.getPickupLongitude(),
                            driver.getLatitude(), driver.getLongitude());
                    return distance <= 10.0; // example distance filter within 10 km
                })
                .collect(Collectors.toList());

        DriverResponse response = new DriverResponse();
        response.setAvailableDrivers(availableDrivers);
        return response;
    }
}