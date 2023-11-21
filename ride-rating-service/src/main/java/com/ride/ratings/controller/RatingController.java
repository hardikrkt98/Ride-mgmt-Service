package com.ride.ratings.controller;


import com.ride.ratings.dto.DriverAvgRating;
import com.ride.ratings.dto.RideRating;
import com.ride.ratings.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<?> rateRide(@RequestBody RideRating rideRating){
        ratingService.saveRating(rideRating);
        return ResponseEntity.created(URI.create("/rating?rideId="+rideRating.rideId())).build();

    }

    @GetMapping
    public ResponseEntity<?> getRating(@RequestParam("rideId") String rideId){
        RideRating rideRating=ratingService.getRating(rideId);
        if(rideRating==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rideRating);

    }

    @GetMapping("/avg/rating")
    public ResponseEntity<?> getDriverRating(@RequestParam("driverId") String driverId){

        DriverAvgRating driverAvgRating=ratingService.getDriverRating(driverId);
        return ResponseEntity.ok(driverAvgRating);
    }
}
