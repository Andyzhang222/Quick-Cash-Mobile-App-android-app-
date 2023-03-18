package com.example.quickcash.LocationTracker;

import android.annotation.SuppressLint;
import android.content.Context;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;


import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationServices;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A class for tracking the device's current location
 * Uses the FusedLocationProviderClient from the Google Play Services Location APIs for location tracking.
 */
public class LocationTracker implements LocationProvider {

    private static final String TAG = "LocationProvider";
    private final FusedLocationProviderClient fusedLocationClient;
    Context context;

    /**
     * Constructor for LocationTracker class.
     * @param context Context of the activity or service using the LocationTracker.
     */
    public LocationTracker(Context context) {
        this.context = context;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }



    /**
     * Gets the last known location of the device.
     * @param locationSuccessListener Listener to receive the location.
     */
    @Override
    @SuppressLint("MissingPermission")
    public void getLastLocation(OnSuccessListener<Location> locationSuccessListener) {
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(locationSuccessListener);
        task.addOnFailureListener(e -> Log.d(TAG, "Failed to get current location: " + e.getMessage()));
    }


    /**
     * Get the city name and country/region name of the current device location.
     *
     * @param localAreaListener An OnSuccessListener to receive the city name and country/region name.
     *                          If the device's location cannot be obtained, null will be returned.
     */
    @Override
    public void getLocalArea(OnSuccessListener<String> localAreaListener) {
        // Get the last known location of the device
        getLastLocation(location -> {
            // Use the Geocoder class to get address information from the location
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                // If the Geocoder successfully returned at least one address, extract the city name and country name from the address
                if (!addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    String cityName = address.getLocality();
                    String countryName = addresses.get(0).getCountryName();
                    localAreaListener.onSuccess(cityName+" "+countryName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}



