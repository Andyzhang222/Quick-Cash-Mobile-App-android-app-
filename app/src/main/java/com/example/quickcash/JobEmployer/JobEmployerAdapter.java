package com.example.quickcash.JobEmployer;

import android.view.LayoutInflater;
import android.view.View;
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
        View itemView = LayoutInflater.from(context).inflate(R.layout.job_employer_view,parent,false);
        String status = "";
        String jobEmployerId = "";
        String employeeId = "";
        return new MyJobViewHolder(itemView,status,jobEmployerId,employeeId);
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
        holder.jobEmployerId = jobEmployerList.get(position).getJobId();

        holder.employeeId = jobEmployerList.get(position).getEmployeeID();

        JobEmployer jobEmployer = jobEmployerList.get(position);
        //get jobId
        holder.jobEmployerId = jobEmployer.getJobId();
        //get jobStatus
        String status = jobEmployer.getStatus();

        String employeeId = jobEmployer.getEmployeeID();

       // System.out.println("test for if get the emploee id  and status " + jobEmployer.getEmployeeID() + "dddd" + status);

        //pass the jobStatus
        holder = new MyJobViewHolder(holder.itemView,status,jobEmployer.getJobId(),employeeId);
    }

    @Override
    public int getItemCount() {
        return jobEmployerList.size();
    }
}
