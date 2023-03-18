package com.example.quickcash.LocationDistance;

import android.location.Location;

/**
 * An interface that defines the method signature for calculating the distance between two Location objects.
 */
public interface LocationCalculator {

    /**
     * Calculates the distance between two Location objects.
     * @param location1 the first Location object
     * @param location2 the second Location object
     * @return the distance between the two Location objects
     */
    double calculateDistance(Location location1, Location location2);
}
