package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewJobsActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button viewJobsButton;
    private DatabaseReference jobPostDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);


        viewJobsButton = findViewById(R.id.viewJobsButton);
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        jobPostDb = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);


    }
}