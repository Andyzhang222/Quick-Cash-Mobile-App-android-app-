package com.example.quickcash.LocationDistance;

import android.location.Location;

/**
 * A class that implements the LocationCalculator interface to calculate the distance
 * between two Location objects using the distanceTo method provided by the Android Location class.
 */
public class ILocationCalculator implements LocationCalculator{
    /**
     * Calculates the distance between two Location objects using the distanceTo method
     * provided by the Android Location class.
     * @param location1 the first Location object
     * @param location2 the second Location object
     * @return the distance between the two Location objects in meters
     */
    @Override
    public double calculateDistance(Location location1, Location location2) {
        return location1.distanceTo(location2);
    }
}
