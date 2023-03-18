package com.example.quickcash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitJobsActivity extends AppCompatActivity{
    private EditText editTextJobType, editTextDescription, editTextPlace, editTextDate, editTextDuration, editTextSalary;
    private CheckBox checkBoxUrgency;
    private Button submitButton;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference mJobs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_jobs);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        String uId = mUser.getUid();

        mJobs = FirebaseDatabase.getInstance().getReference().child("Job Post");

        submitJob();

    }

    public void submitJob(){
        editTextJobType = findViewById(R.id.Job_type);
        editTextDescription = findViewById(R.id.Description);
        editTextPlace = findViewById(R.id.Place);
        editTextDate = findViewById(R.id.date);
        editTextDuration = findViewById(R.id.duration);
        editTextSalary = findViewById(R.id.salary);
        checkBoxUrgency = findViewById(R.id.emergency_select);

        submitButton = findViewById(R.id.Submit_job_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jobType = editTextJobType.getText().toString();
                String description = editTextDescription.getText().toString();
                String date = editTextDate.getText().toString();
                String duration = editTextDuration.getText().toString();
                String place = editTextPlace.getText().toString();
                String salary = editTextSalary.getText().toString();
                Boolean urgency = checkBoxUrgency.isChecked();

                //String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                if (TextUtils.isEmpty(jobType)){
                    editTextJobType.setError("Require Field...");
                    return;
                }
                if (TextUtils.isEmpty(description)){
                    editTextDescription.setError("Require Field...");
                    return;
                }
                if (TextUtils.isEmpty(date)){
                    editTextDate.setError("Require Field...");
                    return;
                }
                if (TextUtils.isEmpty(duration)){
                    editTextDuration.setError("Require Field...");
                    return;
                }
                if (TextUtils.isEmpty(place)){
                    editTextPlace.setError("Require Field...");
                    return;
                }
                if (TextUtils.isEmpty(salary)){
                    editTextSalary.setError("Require Field...");
                    return;
                }

                //String id = mJobs.push().getKey();
                String id = getIntent().getStringExtra("userId");

                Job job = new Job(id,
                        jobType,
                        description,
                        date,
                        duration,
                        place,
                        urgency,
                        30,
                        ""
                );

                mJobs.child(id).setValue(job);

                Toast.makeText(getApplicationContext(),"Post Job Successfully",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(SubmitJobsActivity.this, ViewJobsActivity.class);
                String userId = getIntent().getStringExtra("userId");
                intent.putExtra("userId",userId);
                startActivity(intent);
                finish();
                //跳转到查看Job页面
            }
        });
    }

}