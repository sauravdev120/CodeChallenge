package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.example.demo.validator.TransmissionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private String id;
    private String vin;
    private int year;
    private String make;
    private String model;
    @TransmissionType
    private String transmissionType;
}
