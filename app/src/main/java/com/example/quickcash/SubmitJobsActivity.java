package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quickcash.JobEmployer.JobEmployer;
import com.example.quickcash.LocationTracker.LocationTracker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitJobsActivity extends AppCompatActivity{
    public static final String REQUIRE_FIELD = "Require Field...";

    private DatabaseReference mJobs;
    String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_jobs);


        mJobs = FirebaseDatabase.getInstance().getReference().child("Job Post");
        LocationTracker locationTracker = new LocationTracker(this);
        locationTracker.startTracking(location -> {
            area = locationTracker.getLocalArea(location);
            locationTracker.stopTracking();
        });




        submitJob();

    }

    public void submitJob(){
        EditText editTextJobType;
        EditText editTextDescription;

        EditText editTextPlace;


        EditText editTextDate;
        EditText editTextDuration;
        EditText editTextSalary;
        CheckBox checkBoxUrgency;
        editTextJobType = findViewById(R.id.Job_type);
        editTextDescription = findViewById(R.id.Description);
        editTextPlace = findViewById(R.id.Place);



        //System.out.println("-------------------" + area);

        editTextPlace.setText(area);






        editTextDate = findViewById(R.id.date);
        editTextDuration = findViewById(R.id.duration);
        editTextSalary = findViewById(R.id.salary);
        checkBoxUrgency = findViewById(R.id.emergency_select);

        Button submitButton = findViewById(R.id.Submit_job_button);
        submitButton.setOnClickListener(view -> {
            String jobType = editTextJobType.getText().toString();
            String description = editTextDescription.getText().toString();
            String date = editTextDate.getText().toString();
            String duration = editTextDuration.getText().toString();
            String place = area;
            String salary = editTextSalary.getText().toString();
            Boolean urgency = checkBoxUrgency.isChecked();
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            if (TextUtils.isEmpty(jobType)){
                editTextJobType.setError(REQUIRE_FIELD);
                return;
            }
            if (TextUtils.isEmpty(description)){
                editTextDescription.setError(REQUIRE_FIELD);
                return;
            }
            if (TextUtils.isEmpty(date)){
                editTextDate.setError(REQUIRE_FIELD);
                return;
            }
            if (TextUtils.isEmpty(duration)){
                editTextDuration.setError(REQUIRE_FIELD);
                return;
            }
//            if (TextUtils.isEmpty(place)){
//                editTextPlace.setError(REQUIRE_FIELD);
//                return;
//            }
            if (TextUtils.isEmpty(salary)){
                editTextSalary.setError(REQUIRE_FIELD);
                return;
            }

            String jobId = mJobs.push().getKey();

            JobEmployer jobEmployer = new JobEmployer(userId,
                    jobType,
                    description,
                    date,
                    duration,
                    place,
                    urgency,
                    Integer.parseInt(salary),
                    "Open",
                    "111"
            );

            mJobs.child(jobId).setValue(jobEmployer);

            Toast.makeText(getApplicationContext(),"Post Job Successfully",Toast.LENGTH_LONG).show();

            Intent switchIntent = new Intent(getApplicationContext(),EmployerPage.class);
            startActivity(switchIntent);
            finish();

        });
    }



}