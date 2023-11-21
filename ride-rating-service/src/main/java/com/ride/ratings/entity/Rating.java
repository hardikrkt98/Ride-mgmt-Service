package com.ride.ratings.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity(name = "rating")
public class Rating  implements Serializable {

    @Id
    @Column(name = "rideId")
    private String rideId;

    @Column(name="driverId")
    private String driverId;

    @Column(name="rating")
    private Double rating;

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
