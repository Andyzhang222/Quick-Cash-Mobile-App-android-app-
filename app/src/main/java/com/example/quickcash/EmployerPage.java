/**
 * This activity is used for employer activity
 * Editor: Zhiqiang Yu
 */

package com.example.quickcash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class EmployerPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_page);

        //implement the log out button function
        //when user click the button, we should log out
        //After that we redirect to the login page
        Button logoutBtn = findViewById(R.id.LogoutButton2);
        Button postBtn = findViewById(R.id.post);
        Button checkPostedJob = findViewById(R.id.checkPostedJob);
        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(),LoginPage.class);
            startActivity(intent);
            finish();
        });


        postBtn.setOnClickListener(v -> {
            Intent switchIntent = new Intent(getApplicationContext(),SubmitJobsActivity.class);
            startActivity(switchIntent);
            finish();
        });

        checkPostedJob.setOnClickListener(v -> {
            Intent intent = new Intent(EmployerPage.this, ViewJobsActivity.class);
            startActivity(intent);
        });


    }
    /**
     * This method is called when the activity is starting. It checks if the current user has set a username.
     * If the username is not set, it displays the UsernameDialog for the user to set their username.
     */
    @Override
    protected void onStart() {
        super.onStart();
        UsernameDialog.checkIfUsernameSet(this, username -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if (currentUser != null) {
                String userId = currentUser.getUid();
                UsernameDialog.setUserUsername(userId, username);
            }
        });
    }
}