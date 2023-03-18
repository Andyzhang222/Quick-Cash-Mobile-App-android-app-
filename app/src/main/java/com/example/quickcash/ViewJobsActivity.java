package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
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
    private FirebaseAuth mAuth;
    private Button viewJobsButton;
    private DatabaseReference jobPostDb;
    private RecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_jobs);

        recyclerView = findViewById(R.id.mRecyclerView);


        List<Job> jobList = new ArrayList<>();


        jobList.add(new Job("123456",
                "babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "University Street",
                false,
                30,
                ""
        ));

        jobList.add(new Job("123456",
                "babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "University Street",
                false,
                30,
                ""
        ));

        jobList.add(new Job("123456",
                "babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "University Street",
                false,
                30,
                ""
        ));

        jobList.add(new Job("123456",
                "babysitter",
                "Babysitting and playing with baby",
                "2023-05-20",
                "More than two hours",
                "University Street",
                false,
                30,
                ""
        ));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new JobAdapter(getApplicationContext(),jobList));

//        public void onClick(View view) {
//
//
//
//        }
//            String jobType =
//            String description = editTextDescription.getText().toString();
//            String date = editTextDate.getText().toString();
//            String duration = editTextDuration.getText().toString();
//            String place = editTextPlace.getText().toString();
//            String salary = editTextSalary.getText().toString();
//            Boolean urgency = checkBoxUrgency.isChecked();



            //String id = mJobs.push().getKey();
//            String id = getIntent().getStringExtra("userId");
//
//            Job job = new Job(id,
//                    jobType,
//                    description,
//                    date,
//                    duration,
//                    place,
//                    urgency,
//                    30,
//                    ""
//            );


//            String userId = getIntent().getStringExtra("userId");

//        JobRepository jobRepository = new JobRepository();
//
//        jobRepository.getJobsByUserId(userId);

//        recyclerView = findViewById(R.id.job_title);




//        viewJobsButton = findViewById(R.id.viewJobsButton);
//        mAuth = FirebaseAuth.getInstance();
//
//        FirebaseUser mUser = mAuth.getCurrentUser();
//        String uId = mUser.getUid();
//
//        jobPostDb = FirebaseDatabase.getInstance().getReference().child("Job Post").child(uId);


    }
}