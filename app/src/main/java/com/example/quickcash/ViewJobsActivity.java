package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewJobsActivity extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//    private Button viewJobsButton;
//    private DatabaseReference jobPostDb;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        System.out.println("ddddddddd");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        //从submitActivity里面获取JobList
        Intent intent = getIntent();
        JobRepository jobRepository = (JobRepository) intent.getSerializableExtra("JobRepository");
        //从上一层的jobRepository里面，提取Job然后存在下面这个List里面
        List<Job> jobs = jobRepository.getJobList();

        recyclerView = findViewById(R.id.mRecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new JobAdapter(getApplicationContext(),jobs));
    }
}