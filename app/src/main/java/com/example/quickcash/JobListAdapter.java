package com.example.quickcash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
        return new JobListHolder(LayoutInflater.from(context).inflate(R.layout.job_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull JobListHolder holder, int position) {
        holder.title.setText(jobs.get(position).getJobTitle());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }
}
