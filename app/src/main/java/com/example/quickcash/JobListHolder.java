package com.example.quickcash;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JobListHolder extends RecyclerView.ViewHolder {

    TextView title;

    public JobListHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
    }
}
