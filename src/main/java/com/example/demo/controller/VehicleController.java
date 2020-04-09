package com.example.demo.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.model.Vehicle;
import com.example.demo.service.VehicleService;

@RestController
@RequestMapping(VehicleController.URL)
public class VehicleController {

    static final String URL = "/vehicle-api/v1/vehicles";
    private static Logger logger = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicle")
    public ResponseEntity<?> createVehicle(UriComponentsBuilder b, @Valid @RequestBody Vehicle vehicle) {
        logger.info("Request payload: {}", vehicle);
        CompletableFuture<Vehicle> saved = vehicleService.save(vehicle);
        UriComponents uriComponents = null;
        try {
            uriComponents = b.path("{id}").buildAndExpand(saved.get().getId());
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error getting vehicle: {}", e.getLocalizedMessage());
            return new ResponseEntity<>("Error saving vehicle", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
}
