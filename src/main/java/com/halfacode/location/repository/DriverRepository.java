package com.halfacode.location.repository;


import com.halfacode.location.dto.DriverDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DriverRepository {
    private List<DriverDTO> drivers;

    public DriverRepository() {
        drivers = new ArrayList<>();
        drivers.add(new DriverDTO("John Doe", "Toyota Prius", true, 34.0522, -118.2437));
        drivers.add(new DriverDTO("Jane Smith", "Honda Civic", false, 34.0522, -118.2437));
        drivers.add(new DriverDTO("Bob Brown", "Ford Focus", true, 34.0522, -118.2437));
    }

    public List<DriverDTO> findAll() {
        return drivers;
    }
}
