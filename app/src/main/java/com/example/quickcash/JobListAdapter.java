package com.example.quickcash;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobListAdapter extends RecyclerView.Adapter<JobListHolder> {

    Context context;
    List<Job> jobs;

    public JobListAdapter(Context context, List<Job> jobs) {
        this.context = context;
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public JobListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflated layout will be attached to the employee page
        return new JobListHolder(LayoutInflater.from(context).inflate(R.layout.job_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JobListHolder holder, int position) {
        holder.title.setText(jobs.get(position).getJobTitle());
//        holder.JobLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent switchIntent = new Intent(context, JobPosting.class);
//                //context.startActivity(switchIntent);
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }
}
