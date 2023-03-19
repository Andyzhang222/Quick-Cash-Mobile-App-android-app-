package com.example.quickcash;
/**
 * This class is for reference to small layout of one job
 * Editor: Ziyue Wang, Guanxiang Wang
 */

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
//Used S of the SOLID Principle
public class JobListHolder extends RecyclerView.ViewHolder {

    TextView title;
    ConstraintLayout JobLayout;


    public JobListHolder(@NonNull View itemView) {
        super(itemView);

        // Set up the views in the layout
        title = itemView.findViewById(R.id.title);
        JobLayout = itemView.findViewById(R.id.oneLineJobTitle);

        //implement when user click each job's block, then it job to JobPosting.class
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent switchIntent = new Intent(view.getContext(), JobPosting.class);
                switchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(switchIntent);

            }
        });
    }
}
