CREATE TABLE `rating` (
                          `rideId` varchar(100) NOT NULL,
                          `userId` varchar(100) NOT NULL,
                          `rating` double NOT NULL,
                          PRIMARY KEY (`rideId`),
                          KEY `rating_rideId_IDX` (`rideId`) USING BTREE
);

create or replace view driverAvgRatingView as
SELECT driverId,avg(rating) as avgRating from rating group by  driverId ;