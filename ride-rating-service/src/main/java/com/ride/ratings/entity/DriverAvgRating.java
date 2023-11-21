package com.ride.ratings.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity(name = "driveravgratingview")
public class DriverAvgRating implements Serializable {

    @Id
    @Column(name="driverId")
    private String driverId;

    @Column(name="avgRating")
    private Double avgRating;

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }
}
