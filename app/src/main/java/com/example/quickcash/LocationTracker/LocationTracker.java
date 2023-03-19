package com.example.quickcash.LocationTracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A class for tracking the device's current location and getting the address corresponding to the location.
 * Uses the FusedLocationProviderClient from the Google Play Services Location APIs for location tracking.
 */
public class LocationTracker implements LocationProvider {

    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private Context context;
    private OnSuccessListener<Location> locationSuccessListener;
    private boolean isTracking = false;

    /**
     * Constructor for LocationTracker class.
     * @param context Context of the activity or service using the LocationTracker.
     */
    public LocationTracker(Context context) {
        this.context = context;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null&&locationSuccessListener != null) {
                        locationSuccessListener.onSuccess(location);
                    }
                }
            }
        };
    }

    /**
     * Starts tracking the device's current location.
     * @param locationSuccessListener Listener to receive location updates.
     */
    @SuppressLint("MissingPermission")
    public void startTracking(OnSuccessListener<Location> locationSuccessListener) {
        this.locationSuccessListener = locationSuccessListener;
        isTracking = true;
        getLastLocation(locationSuccessListener);
        LocationRequest locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,10000).build();

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    /**
     * Gets the last known location of the device.
     * @param locationSuccessListener Listener to receive the location.
     */
    @Override
    @SuppressLint("MissingPermission")
    public void getLastLocation(OnSuccessListener<Location> locationSuccessListener) {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        locationSuccessListener.onSuccess(location);
                    }
                });
    }

    /**
     * Stops tracking the device's current location.
     */
    public void stopTracking() {
        if (isTracking) {
            fusedLocationClient.removeLocationUpdates(locationCallback);
            isTracking = false;
        }
    }

    /**
     * Gets the address corresponding to the given location.
     * @param location Location object for which the address is needed.
     * @return String containing the address corresponding to the given location.
     */
    public String getLocalArea(Location location) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert addresses != null;
        if (!addresses.isEmpty()) {
            Address address = addresses.get(0);
            return address.getLocality()+", "+address.getAdminArea()+", "+address.getCountryName();
        }

        return null;
    }
}



