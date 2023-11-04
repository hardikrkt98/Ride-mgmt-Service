package com.ride.ratings.service;

import com.ride.ratings.dto.DriverAvgRating;
import com.ride.ratings.dto.RideRating;
import com.ride.ratings.entity.Rating;
import com.ride.ratings.repository.DriverAvgRatingRepository;
import com.ride.ratings.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService{

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private DriverAvgRatingRepository driverAvgRatingRepository;

    @Override
    public void saveRating(RideRating rideRating) {
        Rating rating=new Rating();
        rating.setRideId(rideRating.rideId());
        rating.setDriverId(rideRating.driverId());
        rating.setRating(rideRating.rating());
        ratingRepository.save(rating);

    }

    @Override
    public RideRating getRating(String rateId) {
        Optional<Rating> ratingOptional=ratingRepository.findById(rateId);
        if(ratingOptional.isEmpty()){
            return null;
        }
        Rating rating=ratingOptional.get();
        return new RideRating(rating.getRideId(),rating.getDriverId(),rating.getRating());
    }

    @Override
    public DriverAvgRating getDriverRating(String driverId) {
        Optional<com.ride.ratings.entity.DriverAvgRating> ratingOptional=driverAvgRatingRepository.findById(driverId);
        if(ratingOptional.isEmpty()){
            return new DriverAvgRating(driverId,0);
        }
        com.ride.ratings.entity.DriverAvgRating driverAvgRating=ratingOptional.get();
        return new DriverAvgRating(driverAvgRating.getDriverId(), driverAvgRating.getAvgRating());
    }
}
