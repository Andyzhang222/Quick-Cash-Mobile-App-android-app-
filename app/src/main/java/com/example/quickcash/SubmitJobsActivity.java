package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SubmitJobsActivity extends AppCompatActivity{
    private EditText editTextJobType, editTextDescription, editTextPlace, editTextDate, editTextDuration, editTextSalary;
    private CheckBox checkBoxUrgency;
    private Button submitButton;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Jobs").child("哈哈");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_jobs);

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

                reference.setValue(jobType);
            }
        });


    }

}