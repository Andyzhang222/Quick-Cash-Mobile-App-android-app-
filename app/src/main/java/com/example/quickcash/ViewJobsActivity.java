package com.example.quickcash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quickcash.JobEmployer.JobEmployer;
import com.example.quickcash.JobEmployer.JobEmployerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewJobsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference mJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        recyclerView = findViewById(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mJobs = FirebaseDatabase.getInstance().getReference().child("Job Post");
        ValueEventListener allJobsListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<JobEmployer> jobEmployers = new ArrayList<>();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    if(userId.equals(userSnapshot.child("employerId").getValue())){
                        jobEmployers.add(userSnapshot.getValue(JobEmployer.class));
                        recyclerView.setAdapter(new JobEmployerAdapter(getApplicationContext(), jobEmployers));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mJobs.addListenerForSingleValueEvent(allJobsListener);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button back_to_profile_btn = findViewById(R.id.back2pro);
        back_to_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(ViewJobsActivity.this,EmployerPage.class);
                startActivity(intent);
                finish();
            }
        });

    }
}