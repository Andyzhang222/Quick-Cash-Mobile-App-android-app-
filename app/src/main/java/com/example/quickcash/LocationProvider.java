package com.example.quickcash;

import android.location.Location;

import com.google.android.gms.tasks.OnSuccessListener;

public interface LocationProvider {
    void startTracking(OnSuccessListener<Location> locationSuccessListener);
    void stopTracking();
}
