package com.example.demo.service;

import java.util.concurrent.CompletableFuture;

import com.example.demo.model.Vehicle;

public interface VehicleService {
    CompletableFuture<Vehicle> save(Vehicle vehicle);
}
