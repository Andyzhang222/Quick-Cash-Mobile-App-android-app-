package com.example.quickcash;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

    private FirebaseAuth auth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference reference = db.getReference().child("Jobs");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_jobs);

        auth = FirebaseAuth.getInstance();

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

//                db.getReference("U")
//
//                reference.child("UserId").setValue(userID);

//                FirebaseUser user = auth.getCurrentUser();
//
//                user.getUid();

                // 获取传递过来的 Intent 对象
//                Intent intent = getIntent();
//
//
//
//                // 从 Intent Extra 中获取用户 ID
//                String userId = intent.getStringExtra("userId");



//                reference.child("UserID").setValue("12345");

                Job job = new Job("123456",
                        jobType,
                        description,
                        date,
                        duration,
                        place,
                        urgency,
                        30,
                        ""
                );


                FirebaseDatabase.getInstance().getReference("Jobs").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(job)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(SubmitJobsActivity.this, "Job has been post successfully", Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(SubmitJobsActivity.this, "Fail to Submit Job! Try again!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

//                reference.child("UserId").setValue(userId);
//
//
//                reference.child("JobType").setValue(jobType);
//                reference.child("Description").setValue(description);
//                reference.child("Date").setValue(date);
//                reference.child("Duration").setValue(duration);
//                reference.child("Place").setValue(place);
//                reference.child("Salary").setValue(salary);
//                reference.child("Urgency").setValue(urgency);
//                System.out.println("======================"+userId);
            }
        });


    }

}