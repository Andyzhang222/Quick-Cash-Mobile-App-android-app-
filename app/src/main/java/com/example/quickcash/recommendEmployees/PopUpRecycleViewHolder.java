package com.example.quickcash.recommendEmployees;


/**
 * This class is for reference to small layout of one employees
 * Editor: Ziyue Wang, Guanxiang Wang
 */

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.quickcash.R;

//Used S of the SOLID Principle
public class PopUpRecycleViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    ConstraintLayout jobLayout;


    public PopUpRecycleViewHolder(@NonNull View itemView) {
        super(itemView);

        // Set up the views in the layout
        jobLayout = itemView.findViewById(R.id.popUpRecycleView);
        title = itemView.findViewById(R.id.us2EmployeeTitle);

    }
}
