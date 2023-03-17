package com.example.quickcash;

import android.annotation.SuppressLint;
import android.content.Context;

import android.location.Location;
import android.util.Log;


import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationServices;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


/**
 * A class for tracking the device's current location
 * Uses the FusedLocationProviderClient from the Google Play Services Location APIs for location tracking.
 */
public class LocationTracker implements LocationProvider {

    private static final String TAG = "LocationProvider";
    private final FusedLocationProviderClient fusedLocationClient;

    /**
     * Constructor for LocationTracker class.
     * @param context Context of the activity or service using the LocationTracker.
     */
    public LocationTracker(Context context) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }



    /**
     * Gets the last known location of the device.
     * @param locationSuccessListener Listener to receive the location.
     */
    @SuppressLint("MissingPermission")
    public void getLastLocation(OnSuccessListener<Location> locationSuccessListener) {
        Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(locationSuccessListener);
        task.addOnFailureListener(e -> Log.d(TAG, "Failed to get current location: " + e.getMessage()));
    }




}



