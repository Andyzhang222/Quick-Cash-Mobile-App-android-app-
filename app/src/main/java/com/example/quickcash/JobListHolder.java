package com.example.quickcash;
/**
 * This class is for reference to small layout of one job
 * Editor: Ziyue Wang, Guanxiang Wang
 */

import android.content.Intent;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Used S of the SOLID Principle
public class JobListHolder extends RecyclerView.ViewHolder {






    TextView title;
    String jobId;
    ConstraintLayout jobLayout;

    public JobListHolder(@NonNull View itemView) {
        super(itemView);

        // Set up the views in the layout
        title = itemView.findViewById(R.id.title);
        jobLayout = itemView.findViewById(R.id.oneLineJobTitle);

        //implement when user click each job's block, then it job to JobPosting.class
        itemView.setOnClickListener(view -> {

            Intent switchIntent = new Intent(view.getContext(), JobPosting.class);
            switchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(switchIntent);

        });

        //implement when user click apply button, then text change to applied
        Button applyBtn = itemView.findViewById(R.id.applyButton);
        applyBtn.setOnClickListener(view -> {

            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


            DatabaseReference mJobs = FirebaseDatabase.getInstance().getReference().child("Job Post");
            System.out.println("+++++++++++click the apply button and get the++" + userId);
            mJobs.child(jobId).child("employeeID").setValue(userId);


            //jobPostID.child("status").getValue(String.class);
            applyBtn.setText("Applied");

            String jobId = this.jobId;

            //Get the employee jobId and set to close
            DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference().child("Job Post").child(jobId).child("status");
            jobRef.setValue("Closed");
        });
    }
}
