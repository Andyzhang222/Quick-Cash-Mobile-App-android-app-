package com.example.quickcash.LocationTracker;

import android.location.Location;

import com.google.android.gms.tasks.OnSuccessListener;

public interface LocationProvider {
    void getLastLocation(OnSuccessListener<Location> locationSuccessListener);
    void getLocalArea(OnSuccessListener<String> localAreaListener);
}
