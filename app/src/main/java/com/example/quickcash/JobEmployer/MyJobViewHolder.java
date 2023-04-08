package com.example.quickcash.JobEmployer;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.JobPosting;
import com.example.quickcash.LandingPage;
import com.example.quickcash.LoginPage;
import com.example.quickcash.Paypal;
import com.example.quickcash.R;
import com.example.quickcash.ViewJobsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyJobViewHolder extends RecyclerView.ViewHolder {

    TextView jobType;
    TextView description;
    TextView date;
    TextView duration;
    TextView place;
    TextView urgency;
    TextView salary;
    String jobId;

    public MyJobViewHolder(@NonNull View itemView) {
        super(itemView);

        jobType = itemView.findViewById(R.id.JobType);
        description = itemView.findViewById(R.id.Description);
        date = itemView.findViewById(R.id.Date);
        duration = itemView.findViewById(R.id.postedjjjjj);
        place = itemView.findViewById(R.id.Place);
        urgency = itemView.findViewById(R.id.Urgency);
        salary = itemView.findViewById(R.id.Salary);



//        Button pay_Btn = itemView.findViewById(R.id.Pay_btn);
//        pay_Btn.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), Paypal.class);
//            itemView.getContext().startActivity(intent);

//
//            Intent intent = new Intent(itemView.getContext(), Paypal.class);
//            startActivity(intent);

//        String jobId = this.jobId;
//        System.out.println("jobId-------------"+jobId);
//        DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference().child("Job Post").child(this.jobId).child("status");
//        System.out.println("employerrrrrr----jobId------:"+jobRef.getKey());

        Button payBtn = itemView.findViewById(R.id.Pay_btn);
        payBtn.setOnClickListener(view -> {
            String jobId = this.jobId;
            System.out.println("jobId-------------"+jobId);
        });

        itemView.setOnClickListener(view -> {

            Intent switchIntent = new Intent(view.getContext(), Paypal.class);
            switchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(switchIntent);

        });
    }
}
