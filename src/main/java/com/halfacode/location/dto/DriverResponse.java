package com.halfacode.location.dto;


import java.util.List;


public class DriverResponse {
        private List<DriverDTO> availableDrivers;

        public DriverResponse() {
        }

        public DriverResponse(List<DriverDTO> availableDrivers) {
            this.availableDrivers = availableDrivers;
        }

        public List<DriverDTO> getAvailableDrivers() {
            return availableDrivers;
        }

        public void setAvailableDrivers(List<DriverDTO> availableDrivers) {
            this.availableDrivers = availableDrivers;
        }
    }
