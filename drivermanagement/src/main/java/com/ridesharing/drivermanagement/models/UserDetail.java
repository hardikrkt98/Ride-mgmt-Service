package com.ridesharing.drivermanagement.models;

import jakarta.persistence.*;

@Entity
@Table(name = "user_detail")

public class UserDetail {
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
