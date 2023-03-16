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
            assertTrue(location.getLatitude() != 0);
            assertTrue(location.getLongitude() != 0);
        });

        // wait for a few seconds to receive location updates
        Thread.sleep(5000);

        locationTracker.stopTracking();
    }

    /**
     * Test the getAddressFromLocation method of LocationTracker.
     */
    @Test
    public void testGetAddressFromLocation() {
        //The location we use is the Dalhousie Library.
        Location location = new Location("Dalhousie library");
        location.setLatitude(44.637277);
        location.setLongitude(-63.591195);

        String address = locationTracker.getAddressFromLocation(appContext, location);

        assertNotNull(address);
        assertEquals("6227 University Ave, Halifax, NS B3H 4H8, Canada", address);
    }
}

