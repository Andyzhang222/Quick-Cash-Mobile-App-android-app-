/**
 * This activity is used for employer activity
 * Editor: Zhiqiang Yu
 */

package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EmployeePage extends AppCompatActivity{


    RecyclerView recyclerView;
    List<Job> jobList = new ArrayList<>();
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);
        //connect to the firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();

        //implement the logout button function
        //when user click the button, we should log out
        //After that we redirect to the login page
        Button logoutBtn = findViewById(R.id.LogoutButton1);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();;
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //sample list, delete later


        //sample list, delete llater
        fillJobList();

        //set the layout manager, Arrange the items in a one-dimensional list.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //setting the fixed size of the recycleView
       // recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new JobListAdapter(getApplicationContext(), jobList));


//        for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
//            // Get the ViewHolder object associated with the first item in the RecyclerView
//            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
//            TextView title = viewHolder.itemView.findViewById(R.id.title);
//            title.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent switchIntent = new Intent(EmployeePage.this,JobPosting.class);
//                    startActivity(switchIntent);
//                }
//            });
//        }
//        for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
//            // Get the ViewHolder object associated with the first item in the RecyclerView
//            RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(i);
//            if (viewHolder != null) {
//                TextView title = viewHolder.itemView.findViewById(R.id.title);
//                title.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent switchIntent = new Intent(EmployeePage.this,JobPosting.class);
//                        startActivity(switchIntent);
//                    }
//                });
//            }
//        }



    }

//    @Override
//    public void onClick(View v) {
//        //jump to the employee_page
//        Intent switchIntent = new Intent(EmployeePage.this,JobPosting.class);
//        startActivity(switchIntent);
//    }

    public void fillJobList () {
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
        jobList.add(new Job("aaa"));
    }
}