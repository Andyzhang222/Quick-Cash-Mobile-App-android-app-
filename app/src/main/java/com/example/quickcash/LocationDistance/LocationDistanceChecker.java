package com.example.quickcash.LocationDistance;

import android.location.Location;


public class LocationDistanceChecker {

    private final LocationCalculator locationCalculator;


    public LocationDistanceChecker(LocationCalculator locationCalculator) {
        this.locationCalculator = locationCalculator;
    }



    public boolean isWithinRange(Location location1, Location location2, double range) {
        return false;
    }


}





