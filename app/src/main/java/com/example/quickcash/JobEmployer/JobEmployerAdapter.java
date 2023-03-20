package com.example.quickcash.JobEmployer;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.R;

import java.util.List;

public class JobEmployerAdapter extends RecyclerView.Adapter<MyJobViewHolder> {
    android.content.Context context;
    List<JobEmployer> jobEmployerList;

    public JobEmployerAdapter(android.content.Context context, List<JobEmployer> jobEmployerList) {
        this.context = context;
        this.jobEmployerList = jobEmployerList;
    }

    @NonNull
    @Override
    public MyJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyJobViewHolder(LayoutInflater.from(context).inflate(R.layout.job_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyJobViewHolder holder, int position) {

        holder.jobType.setText(jobEmployerList.get(position).getJobType());
        holder.description.setText(jobEmployerList.get(position).getDescription());
        holder.date.setText(jobEmployerList.get(position).getDate());
        holder.duration.setText(jobEmployerList.get(position).getDuration());
        holder.place.setText(jobEmployerList.get(position).getPlace());
        holder.urgency.setText(Boolean.toString(jobEmployerList.get(position).getUrgency()));
        holder.salary.setText(String.valueOf(jobEmployerList.get(position).getSalary()));


    }

    @Override
    public int getItemCount() {
        return jobEmployerList.size();
    }
}
