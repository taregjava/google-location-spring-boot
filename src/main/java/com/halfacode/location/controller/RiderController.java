package com.halfacode.location.controller;


import com.halfacode.location.dto.DriverResponse;
import com.halfacode.location.dto.RideRequest;
import com.halfacode.location.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rides")
public class RiderController {

    @Autowired
    private DriverService driverService;

    @PostMapping("/request")
    public DriverResponse requestRide(@RequestBody RideRequest rideRequest) {
        return driverService.findAvailableDrivers(rideRequest);
    }
}