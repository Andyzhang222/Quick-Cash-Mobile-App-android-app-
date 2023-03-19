package com.example.quickcash;

import android.content.Context;
import android.location.Location;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.quickcash.LocationTracker.LocationTracker;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * This is a test class for the LocationTracker class.
 */
@RunWith(AndroidJUnit4.class)
public class LocationTrackerTest {

    private Context appContext;//The context of the application.
    private LocationTracker locationTracker;//The location tracker object being tested.

    /**
     * Set up the necessary objects before each test.
     */
    @Before
    public void setUp() {
        appContext = ApplicationProvider.getApplicationContext();
        locationTracker = new LocationTracker(appContext);
    }

    /**
     * Tear down the necessary objects after each test.
     */
    @After
    public void tearDown() {
        locationTracker.stopTracking();
    }

    /**
     * Test the start and stop tracking methods of LocationTracker.
     *
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testStartAndStopTracking() throws Exception {
        locationTracker.startTracking(location -> {
            assertNotNull(location);
            assertNotEquals("0", location.getLatitude()+"");
            assertNotEquals("0", location.getLongitude()+"");
        });



        locationTracker.stopTracking();
    }

    /**
     * Test the getLocalArea method of LocationTracker.
     */
    @Test
    public void testGetAddressFromLocation() {
        //The location we use is the Dalhousie Library.
        Location location = new Location("Dalhousie library");
        location.setLatitude(44.637277);
        location.setLongitude(-63.591195);

        String address = locationTracker.getLocalArea(location);

        assertNotNull(address);
        assertEquals("Halifax, Nova Scotia, Canada", address);
    }
}

