/**
 * This activity is used for employer activity
 * Editor: Zhiqiang Yu
 */

package com.example.quickcash;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class EmployeePage extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);
        //connect to the firebase
        auth = FirebaseAuth.getInstance();

        //implement the logout button function
        LogoutBtn();
    }

    /**
     * This method used to implement the logout button function
     */
    private void LogoutBtn() {
        Button logoutBtn = findViewById(R.id.LogoutButton1);
        //when user click the button, we should log out
        //After that we redirect to the login page
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();;
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();
            }
        });


    }



}