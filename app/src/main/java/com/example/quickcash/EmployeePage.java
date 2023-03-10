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

    boolean exit = false;
    FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    final  static int REQUEST_CODE=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);
        //connect to the firebase
        auth = FirebaseAuth.getInstance();

        //implement the logout button function
        LogoutBtn();
        updateLocation();
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

    /**
     * This method used to show the address of location
     * @param location message of location
     */
    private void showLocationMessage(Location location){
        if(location==null){
            return;
        }
        TextView nowLocation = findViewById(R.id.nowLocation);
        Geocoder geocoder=new Geocoder(EmployeePage.this, Locale.getDefault());
        List<Address> addresses= null;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            nowLocation.setText(addresses.get(0).getAddressLine(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *This method used to update the location every 10s
     */
    private void updateLocation() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(!exit){
                    try {
                        getCurrentLocation();
                        sleep(10*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        thread.start();
    }

    /**
     *This method used to get current location
     */
    private void getCurrentLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            fusedLocationProviderClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY, getCancellationToken()).addOnSuccessListener(location -> {
                currentLocation = location;
                showLocationMessage(currentLocation);
            });
        }else {
            askLocationPermission();
        }
    }

    /**
     * This method is used to create the new cancellation token
     * @return new cancellation token
     */
    @NonNull
    private CancellationToken getCancellationToken() {
        return new CancellationToken() {
            @Override
            public boolean isCancellationRequested() {
                return false;
            }

            @NonNull
            @Override
            public CancellationToken onCanceledRequested(@NonNull OnTokenCanceledListener onTokenCanceledListener) {
                return null;
            }
        };
    }

    /**
     * This method is used to ask the location permission of user
     */
    private void askLocationPermission() {
        ActivityCompat.requestPermissions(EmployeePage.this, new String[]
                {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
    }

    /**
     * This method is used to start get location after get permission
     * @param requestCode The request code passed in
     * @param permissions The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }
            else {
                Toast.makeText(this, "Required Permission", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * This method is when we do not use this activity, we stop getting the location
     */
    @Override
    protected void onPause() {
        super.onPause();
        exit = true;
    }

    /**
     * This method is when we use this activity again, we start getting the location
     */
    @Override
    protected void onResume() {
        super.onResume();
        exit = false;
    }

}