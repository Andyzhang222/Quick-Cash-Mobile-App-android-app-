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
    private DatabaseReference mJobs;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_page);
        //connect to the firebase
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();
        mJobs = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);

        //implement the log out button function
        //when user click the button, we should log out
        //After that we redirect to the login page
        Button logoutBtn = findViewById(R.id.LogoutButton2);
        Button postBtn = findViewById(R.id.post);
        Button checkPostedJob = findViewById(R.id.checkPostedJob);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();;
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();
            }
        });


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchIntent = new Intent(getApplicationContext(),SubmitJobsActivity.class);
                startActivity(switchIntent);
                finish();
            }
        });

        checkPostedJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmployerPage.this, ViewJobsActivity.class);
                startActivity(intent);
            }
        });




    }
}