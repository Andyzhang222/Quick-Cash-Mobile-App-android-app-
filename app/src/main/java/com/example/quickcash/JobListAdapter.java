package com.example.quickcash;
/**
 * The activity is for connect the the data to the views in the job view
 * Editor: Ziyue Wang, Guanxiang Wang
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Used S of the SOLID Principle
//Used I of the SOLID Principle
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

    @NonNull




    /**
     * Bind the data to the views in the job view
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull JobListHolder holder, int position) {
        holder.title.setText(jobs.get(position).getJobTitle());
        holder.jobId = jobs.get(position).getJobId();
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    /**
     * // Filter the job list based on a search query and update the RecyclerView
     * @param filteredJobList the job list after filtered
     */
    public void setFilteredJobList(List<Job> filteredJobList){
        this.jobs = filteredJobList;
        notifyDataSetChanged();
    }
}