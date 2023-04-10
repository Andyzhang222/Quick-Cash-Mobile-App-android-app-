package com.example.quickcash.Explore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.JobEmployer.JobEmployer;
import com.example.quickcash.JobEmployer.JobEmployerAdapter;
import com.example.quickcash.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * An adapter for displaying a list of employer users in a RecyclerView.
 */
public class EmployerAdapter extends RecyclerView.Adapter<EmployerAdapter.UserViewHolder> {

    private List<User> users;

    /**
     * Constructs a new UserAdapter with an empty list of users.
     */
    public EmployerAdapter() {
        this.users = new ArrayList<>();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the user_item layout and return a new UserViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.employer_item, parent, false);
        return new UserViewHolder(view);
    }


    /**
     * Called by RecyclerView to display the data at the specified position.
     * @param holder the UserViewHolder to bind the data to
     * @param position the position of the item within the adapter's data set
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        // Bind the user data to the UserViewHolder
        User user = users.get(position);
        holder.usernameTextView.setText("Name: " +user.getUsername());
        holder.emailTextView.setText("Email: " + user.getEmail());
        holder.ratingTextView.setText("Rating: " + user.getRating());

        holder.itemView.setOnClickListener(v -> {
            String userId = user.getUid();
            DatabaseReference mJobs = FirebaseDatabase.getInstance().getReference().child("Job Post");
            ValueEventListener allJobsListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<JobEmployer> jobEmployers = new ArrayList<>();
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        if (userId.equals(userSnapshot.child("employerId").getValue())) {
                            jobEmployers.add(userSnapshot.getValue(JobEmployer.class));
                        }
                    }
                    showMultipleJobDetailsDialog(v.getContext(), jobEmployers);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // empty
                }
            };
            // Add the ValueEventListener to the DatabaseReference
            mJobs.addListenerForSingleValueEvent(allJobsListener);
        });




    }

    /**
     * Shows a dialog with details of multiple jobs.
     * @param context the context of the dialog
     * @param jobs the list of jobs to display
     */
    @SuppressLint("SetTextI18n")
    private void showMultipleJobDetailsDialog(Context context, List<JobEmployer> jobs) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Job Details");

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        int count = 0;
        for (JobEmployer job : jobs) {
            count++;
            TextView jobItem = new TextView(context);
            String place ="";
            if( job.getPlace() != null){
                place = " at "+job.getPlace();
            }
            jobItem.setText("Job"+count+":\n"+job.getJobType() +place+"\nDescription: "+job.getDescription());
            jobItem.setPadding(16, 16, 16, 16);


            linearLayout.addView(jobItem);
        }

        ScrollView scrollView = new ScrollView(context);
        scrollView.addView(linearLayout);
        builder.setView(scrollView);

        builder.setPositiveButton("Close", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    @Override
    public int getItemCount() {
        return users.size();
    }

    /**
     * Sets the list of users to be displayed in the RecyclerView.
     *
     * @param users The list of users to be displayed.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    /**
     * Clears the list of users from the RecyclerView.
     */
    @SuppressLint("NotifyDataSetChanged")
    public void clear() {
        this.users.clear();
        notifyDataSetChanged();
    }

    /**
     * A ViewHolder for holding the views of a user_item layout.
     */
    static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView usernameTextView;
        TextView emailTextView;
        TextView ratingTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            // Find the views in the user_item layout
            usernameTextView = itemView.findViewById(R.id.username_text_view);
            emailTextView = itemView.findViewById(R.id.email_text_view);
            ratingTextView = itemView.findViewById(R.id.rating_text_view);
        }
    }
}

