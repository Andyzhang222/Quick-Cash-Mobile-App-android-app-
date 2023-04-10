package com.example.quickcash.JobEmployer;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.Paypal;
import com.example.quickcash.R;

public class MyJobViewHolder extends RecyclerView.ViewHolder {

    String employeeId;
    TextView jobType;
    TextView description;
    TextView date;
    TextView duration;
    TextView place;
    TextView urgency;
    TextView salary;
    String jobEmployerId;


    public MyJobViewHolder(@NonNull View itemView, String status,String employerId,String employeeId) {
        super(itemView);

        jobType = itemView.findViewById(R.id.JobType);
        description = itemView.findViewById(R.id.Description);
        date = itemView.findViewById(R.id.Date);
        duration = itemView.findViewById(R.id.postedjjjjj);
        place = itemView.findViewById(R.id.Place);
        urgency = itemView.findViewById(R.id.Urgency);
        salary = itemView.findViewById(R.id.Salary);

        //if the job status is close, the pay button visible
        Button payBtn = itemView.findViewById(R.id.Pay_btn);
        if ("Closed".equals(status)) {
            payBtn.setVisibility(View.VISIBLE);
        } else {
            payBtn.setVisibility(View.GONE);
        }



        //click pay button jump to the PalPay
        payBtn.setOnClickListener(view -> {
            Intent switchIntent = new Intent(view.getContext(), Paypal.class);
            switchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Pass the employerId to next activity
            switchIntent.putExtra("employeeId", employeeId);
            switchIntent.putExtra("employerId", employerId);

            System.out.println("test for both EMPLOEE id get    " + employeeId + "---AND EMPLOYER --" + employerId);
            view.getContext().startActivity(switchIntent);
        });


        itemView.setOnClickListener(view -> {
            Intent switchIntent = new Intent(view.getContext(), Paypal.class);
            switchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            view.getContext().startActivity(switchIntent);
        });
    }
}
