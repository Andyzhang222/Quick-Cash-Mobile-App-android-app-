package com.example.quickcash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.quickcash.R;
import com.example.quickcash.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import com.example.quickcash.R;


public class EmployeeNotification extends AppCompatActivity{


    LinearLayout linear_layout;
    RelativeLayout relative_layout;
    ScrollView scrollView;
    ImageView notification_icon;
    private String activeUser;
    private String jobLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_notifications);

        linear_layout = findViewById(R.id.linear_layout);
        relative_layout = findViewById(R.id.relative_layout);
        scrollView = findViewById(R.id.scrollView);
        notification_icon = findViewById(R.id.notification_icon);

        // get the preference place of employee
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        jobLocation = preferences.getString("job_location", "");

        FirebaseDatabase db = FirebaseDatabase.getInstance();

        // Here should be the URL link of the Firebase
        DatabaseReference reference1 = db.getReferenceFromUrl("https://console.firebase.google.com/project/auth-a7126/database/auth-a7126-default-rtdb/data");
        DatabaseReference reference2 = db.getReferenceFromUrl("https://console.firebase.google.com/project/auth-a7126/database/auth-a7126-default-rtdb/data");

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map map = (Map) snapshot.getValue();
                String message = map.get("message").toString();
                String userType = map.get("user").toString();

                if(userType.equals("employee")){
                    addNotificationBox(message);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Map map = (Map) snapshot.getValue();
                String jobLocation = map.get("job_location").toString();

                // Check whether the new preferred job is near the location
                if (jobLocation.equals(EmployeeNotification.this.jobLocation)) {
                    // if yes, highlight the notification
                    notification_icon.setColorFilter(getResources().getColor(R.color.colorAccent));
                }
            }
        }