package com.example.quickcash;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.quickcash.LocationTracker.LocationTracker;

/**
 * This is a test class for the LocationTracker class.
 */
@RunWith(AndroidJUnit4.class)
public class LocationTrackerTest {

    private LocationTracker locationTracker;//The location tracker object being tested.

    /**
     * Set up the necessary objects before each test.
     */
    @Before
    public void setUp() {
        //The context of the application.
        Context appContext = ApplicationProvider.getApplicationContext();
        locationTracker = new LocationTracker(appContext);
    }

    /**
     * Tear down the necessary objects after each test.
     */
    @After
    public void tearDown() {
    }

    /**
     * Test the start and stop tracking methods of LocationTracker.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testGetLocation() throws Exception {
        locationTracker.getLastLocation(location -> {
            assertNotNull(location);
            assertTrue(location.getLatitude() >= -90 && location.getLatitude() <= 90);
            assertTrue(location.getLongitude() >= -180 && location.getLongitude() <= 180);
        });

    }
}

