package com.ridesharing.drivermanagement.models;

import jakarta.persistence.*;

@Entity
@Table(name = "driver_detail")

public class DriverDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phone;
    private String address;
    private String license;
    private String vechileType;
    private String vechileNumber;
    private String vehicleColor;
    private String vehicleModel;

}
