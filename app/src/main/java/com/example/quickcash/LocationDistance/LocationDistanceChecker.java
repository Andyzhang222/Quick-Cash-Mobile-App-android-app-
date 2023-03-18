package com.example.quickcash.LocationDistance;

import android.location.Location;

/**
 * This class checks if the distance between two locations is within a specified range.
 */
public class LocationDistanceChecker {

    //The calculator used to compute the distance between two locations.
    private final LocationCalculator locationCalculator;


    /**
     * Creates a new instance of the {@link LocationDistanceChecker} class.
     * @param locationCalculator The calculator used to compute the distance between two locations.
     */
    public LocationDistanceChecker(LocationCalculator locationCalculator) {
        this.locationCalculator = locationCalculator;
    }

    /**
     * Checks that the distance between two positions is within the specified range.
     * @param location1 The first location.
     * @param location2 The second location.
     * @param range The specified distance range.
     * @return Returns true if the distance between the two location is less than or equal to the specified range; otherwise returns false.
     */
    public boolean isWithinRange(Location location1, Location location2, double range) {
        double distance = locationCalculator.calculateDistance(location1, location2);
        return distance <= range;
    }


}





