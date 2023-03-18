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
import android.widget.Toast;

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


        System.out.println("ddddddddd");


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



        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button back_to_profile_btn = findViewById(R.id.button2);
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