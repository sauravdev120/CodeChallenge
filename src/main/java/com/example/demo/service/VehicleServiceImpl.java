package com.example.demo.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.model.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService {

    private static Logger logger = LoggerFactory.getLogger(VehicleServiceImpl.class);

    @Async("asyncExecutor")
    @Override
    public CompletableFuture<Vehicle> save(Vehicle vehicle) {
        logger.info("Vehicle save {}", vehicle);
        vehicle.setId(UUID.randomUUID().toString());
        logger.info("Vehicle update {}", vehicle);
        return CompletableFuture.completedFuture(vehicle);
    }
}
