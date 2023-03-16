package com.example.quickcash;

import android.annotation.SuppressLint;
import android.content.Context;

import android.location.Location;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.tasks.OnSuccessListener;

public class LocationTracker {

    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private Context context;
    private OnSuccessListener<Location> locationSuccessListener;
    private boolean isTracking = false;

    public LocationTracker(Context context) {
        //buggy function,fix it
    }

    @SuppressLint("MissingPermission")
    public void startTracking(OnSuccessListener<Location> locationSuccessListener) {
        //buggy function,fix it
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(OnSuccessListener<Location> locationSuccessListener) {
        //buggy function,fix it
    }

    public void stopTracking() {
        //buggy function,fix it
    }

    public String getAddressFromLocation(Context context,Location location) {
        //buggy function,fix it

        return null;
    }
}



