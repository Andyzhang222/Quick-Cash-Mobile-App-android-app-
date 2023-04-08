package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickcash.JobEmployer.JobEmployer;
import com.example.quickcash.LocationTracker.LocationTracker;
//import com.example.quickcash.recommendEmployees.PopUpEmployee;
import com.example.quickcash.recommendEmployees.PopUpRecycleAdapter;
import com.example.quickcash.recommendEmployees.PopUpRecycleViewHolder;
import com.example.quickcash.recommendEmployees.US2FillInEmployeeList;
import com.example.quickcash.recommendEmployees.us2_Employee;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SubmitJobsActivity extends AppCompatActivity{
    public static final String REQUIRE_FIELD = "Require Field...";

    private DatabaseReference mJobs;
    String area;

    Dialog dialog;

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

        dialog = new Dialog(this);
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
            if (TextUtils.isEmpty(place)){
                editTextPlace.setError(REQUIRE_FIELD);
                return;
            }
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
                    "Open"
            );

            mJobs.child(jobId).setValue(jobEmployer);


            Toast.makeText(getApplicationContext(),"Post Job Successfully",Toast.LENGTH_LONG).show();


            openDialog();



        });
    }

    /**
     * Pop up recommended employee list
     */
    private void openDialog() {

        dialog.setContentView(R.layout.activity_pop_up_employee);

        RecyclerView employeeViewList = (RecyclerView) dialog.findViewById(R.id.popUpRecycleView);

        RecyclerView.LayoutManager layoutManager;
        PopUpRecycleAdapter popUpRecycleAdapter;
        //set the layout manager, Arrange the items in a one-dimensional list.
        layoutManager = new LinearLayoutManager(this);
        employeeViewList.setLayoutManager(layoutManager);

        //create a list for the list to pop up
        US2FillInEmployeeList us2FillInEmployeeList = new US2FillInEmployeeList();
        List<us2_Employee> employeeList = new ArrayList<>();
        us2FillInEmployeeList.fillInList(employeeList);

        popUpRecycleAdapter = new PopUpRecycleAdapter(this, employeeList);
        employeeViewList.setAdapter(popUpRecycleAdapter);

        Button closeButton = (Button) dialog.findViewById(R.id.popUpClosedButton);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Intent switchIntent = new Intent(getApplicationContext(),EmployerPage.class);
                startActivity(switchIntent);

                finish();
            }
        });

        dialog.show();


    }


}