/**
 * This activity is used for employer activity
 * Editor: Zhiqiang Yu
 */

package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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

public class EmployeePage extends AppCompatActivity {


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

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        //sample list, delete later
        List<Job> jobList;

        //sample list, delete llater
        jobList = new ArrayList<>();
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


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new JobListAdapter(getApplicationContext(), jobList));

    }
}