package com.ride.ratings.repository;

import com.ride.ratings.entity.DriverAvgRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverAvgRatingRepository extends JpaRepository<DriverAvgRating,String> {
}
