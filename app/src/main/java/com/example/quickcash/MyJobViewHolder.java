package com.example.quickcash;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyJobViewHolder extends RecyclerView.ViewHolder {

    TextView jobType;
    TextView description;
    TextView date;
    TextView duration;
    TextView place;
    TextView urgency;
    TextView salary;

    public MyJobViewHolder(@NonNull View itemView) {
        super(itemView);

        jobType = itemView.findViewById(R.id.JobType);
        description = itemView.findViewById(R.id.Description);
        date = itemView.findViewById(R.id.Date);
        duration = itemView.findViewById(R.id.Duration);
        place = itemView.findViewById(R.id.Place);
        urgency = itemView.findViewById(R.id.Urgency);
        salary = itemView.findViewById(R.id.Salary);
    }
}
