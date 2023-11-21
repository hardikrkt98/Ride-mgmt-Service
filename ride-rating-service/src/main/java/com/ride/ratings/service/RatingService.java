package com.ride.ratings.service;

import com.ride.ratings.dto.DriverAvgRating;
import com.ride.ratings.dto.RideRating;

public interface RatingService {

    public void saveRating(RideRating rideRating);

    public RideRating getRating(String rateId);

    public DriverAvgRating getDriverRating(String driverId);
}
