package com.example.quickcash;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import android.location.Location;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.quickcash.LocationDistance.ILocationCalculator;
import com.example.quickcash.LocationDistance.LocationCalculator;
import com.example.quickcash.LocationDistance.LocationDistanceChecker;

@RunWith(AndroidJUnit4.class)
public class LocationDistanceCheckerTest {
    private LocationDistanceChecker locationDistanceChecker;

    @Before
    public void setUp() {
        LocationCalculator locationCalculator = new ILocationCalculator();
        locationDistanceChecker = new LocationDistanceChecker(locationCalculator);
    }

    @Test
    public void testIsWithinRange_True() {
        Location location1 = new Location("");
        location1.setLatitude(37.7749);
        location1.setLongitude(-122.4194);

        Location location2 = new Location("");
        location2.setLatitude(37.7749005);
        location2.setLongitude(-122.4192948);

        double range = 100; // 100 meters
        assertTrue(locationDistanceChecker.isWithinRange(location1, location2, range));
        System.out.println(location1.distanceTo(location2));
    }

    @Test
    public void testIsWithinRange_False() {
        Location location1 = new Location("");
        location1.setLatitude(37.7749);
        location1.setLongitude(-122.4194);

        Location location2 = new Location("");
        location2.setLatitude(37.7895);
        location2.setLongitude(-122.4055);

        double range = 1000; //one kilometers
        assertFalse(locationDistanceChecker.isWithinRange(location1, location2, range));
    }
}

