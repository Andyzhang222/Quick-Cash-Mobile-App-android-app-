package com.example.quickcash.JobEmployer;

import android.content.Intent;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyJobViewHolder extends RecyclerView.ViewHolder {

    TextView jobType;
    TextView description;
    TextView date;
    TextView duration;
    TextView place;
    TextView urgency;
    TextView salary;
    String jobId;

    public MyJobViewHolder(@NonNull View itemView, String status) {
        super(itemView);

        jobType = itemView.findViewById(R.id.JobType);
        description = itemView.findViewById(R.id.Description);
        date = itemView.findViewById(R.id.Date);
        duration = itemView.findViewById(R.id.postedjjjjj);
        place = itemView.findViewById(R.id.Place);
        urgency = itemView.findViewById(R.id.Urgency);
        salary = itemView.findViewById(R.id.Salary);

        System.out.println("meipao llll-----11111");
        Button payBtn = itemView.findViewById(R.id.Pay_btn);
        if ("Closed".equals(status)) {
            payBtn.setVisibility(View.VISIBLE);
        } else {
            payBtn.setVisibility(View.GONE);
        }

        System.out.println("paol llll-----00000");


//        Button pay_Btn = itemView.findViewById(R.id.Pay_btn);
//        pay_Btn.setOnClickListener(v -> {
//            Intent intent = new Intent(v.getContext(), Paypal.class);
//            itemView.getContext().startActivity(intent);

//
//            Intent intent = new Intent(itemView.getContext(), Paypal.class);
//            startActivity(intent);

//        String jobId = this.jobId;
//        System.out.println("jobId-------------"+jobId);
       // DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference().child("Job Post").child(this.jobId).child("status");
        //System.out.println(jobRef.getKey()+"----111111111111");
        //        System.out.println("employerrrrrr----jobId------:"+jobRef.getKey());


//        payBtn.setVisibility(View.INVISIBLE);
//        payBtn.setOnClickListener(view -> {
//
//            DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference().child("Job Post").child(this.jobId).child("status");
//            System.out.println(jobRef.getKey()+"----111111111111");
//
//            String jobId = this.jobId;
//            System.out.println("jobId-------------"+jobId);
//
//
//
//        });



        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference().child("Job Post");
//        jobRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String status = dataSnapshot.getValue(String.class);
//                if (status.equals("Closed")) {
//                    payBtn.setVisibility(View.VISIBLE);
//                } else {
//                    payBtn.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Log.e("MyJobViewHolder", "Failed to read value.", databaseError.toException());
//            }
//
//
//        });


        DatabaseReference mJobs = FirebaseDatabase.getInstance().getReference().child("Job Post");

        mJobs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<JobEmployer> jobEmployers = new ArrayList<>();

//
//                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
//                    System.out.println("1111---"+userSnapshot.child("status").getValue());
////                    System.out.println("2222---"+userSnapshot.child("employerId").getValue());
//                    if (userSnapshot.child("status").getValue().equals("Closed")){
//                        payBtn.setText("aaa");
//                    }


//                Button payBtn = itemView.findViewById(R.id.Pay_btn);

//                    if (userId.equals(userSnapshot.child("employerId").getValue()) ) {
////                       userId.equals(userSnapshot.child("employerId").getValue()
//                        if(userSnapshot.child("status").getValue().equals("Closed")){
//                            payBtn.setVisibility(View.VISIBLE);
//                        }{
//
//                            payBtn.setVisibility(View.INVISIBLE);
//                        }
//
//                    }
                }
      //      }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



//        if (false) {
//            payBtn.setVisibility(View.VISIBLE);
//        } else {
//            payBtn.setVisibility(View.INVISIBLE);
//        }

//        payBtn.setVisibility(View.INVISIBLE);







        itemView.setOnClickListener(view -> {

            Intent switchIntent = new Intent(view.getContext(), Paypal.class);
            switchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(switchIntent);

        });
    }
}
