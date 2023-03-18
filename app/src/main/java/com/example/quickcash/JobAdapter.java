package com.example.quickcash;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.core.Context;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<MyJobViewHolder> {

//    Context context;
    android.content.Context context;
    List<Job> jobList;

    public JobAdapter(android.content.Context context, List<Job> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public MyJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyJobViewHolder(LayoutInflater.from(context).inflate(R.layout.job_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyJobViewHolder holder, int position) {
        holder.jobType.setText(jobList.get(position).getJobType());
        holder.description.setText(jobList.get(position).getDescription());
        holder.date.setText(jobList.get(position).getDate());
        holder.duration.setText(jobList.get(position).getDuration());
        holder.place.setText(jobList.get(position).getPlace());
        holder.urgency.setText(Boolean.toString(jobList.get(position).getUrgency()));
        holder.salary.setText(String.valueOf(jobList.get(position).getSalary()));


    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }
}
