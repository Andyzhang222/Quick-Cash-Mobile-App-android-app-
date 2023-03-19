 /**
 * This activity is used for employer activity
 * Editor: Zhiqiang Yu
 */
 package com.example.quickcash;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickcash.JobListAdapter;
import com.example.quickcash.LoginPage;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
 //Used S of the SOLID Principle
public class EmployeePage extends AppCompatActivity{
    private DatabaseReference mDatabaseRef;

    Button saveButton;
    RecyclerView recyclerView;
    SearchView searchView;
    List<Job> jobList = new ArrayList<>();
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    JobListAdapter jobListAdapter;
    List<Job> filteredJobList;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);

        // Initialize the Firebase database reference
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        //connect to the firebase
        auth = FirebaseAuth.getInstance();

        //implement the logout button function
        LogoutBtn();
    }

    /**
     * This method used to implement the logout button function
     */
    private void LogoutBtn() {
        Button logoutBtn = findViewById(R.id.LogoutButton1);
        //when user click the button, we should log out
        //After that we redirect to the login page
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();;
                Intent intent = new Intent(getApplicationContext(), LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        //sample list, delete llater
        fillJobList();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        //implement search view
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText, jobList);
                return true;
            }

        });




        //set the layout manager, Arrange the items in a one-dimensional list.
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        jobListAdapter = new JobListAdapter(getApplicationContext(), jobList);
        recyclerView.setAdapter(jobListAdapter);

        saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get input
                String searchText = searchView.getQuery().toString();

                //get user path
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");

                //get id
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

                userRef.child(userId) .child( "preference").setValue(searchText);

                Toast.makeText(EmployeePage.this, "Search text saved successfully", Toast.LENGTH_SHORT).show();
            }
        });
        //get user path
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");

        //get current user id to get their preference
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //get preference data
        userRef.child(userId).child("preference").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Retrieve the preference data and do something with it
                String preference = dataSnapshot.getValue(String.class);
                // TODO: Do something with the preference data
                SearchView searchView = findViewById(R.id.searchView); // Replace with your search bar ID
                searchView.setQuery(preference, false); // Set the search bar text to the preference data
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });
    }


    public void fillJobList () {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference jobPostRef = rootRef.child("Job Post");

        jobPostRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot jobPostSnapshot) {
                for (DataSnapshot userID : jobPostSnapshot.getChildren()) {
                    for (DataSnapshot jobPostID : userID.getChildren()) {
                        String status = (String) jobPostID.child("status").getValue(String.class);
                        if (status.equals("Open")) {
                            String jobType = (String) jobPostID.child("jobType").getValue(String.class);
                            String description = (String) jobPostID.child("description").getValue(String.class);
                            String salary = jobPostID.child("salary").getValue().toString();
                            String duration = jobPostID.child("duration").getValue().toString();
                            String place = (String) jobPostID.child("place").getValue(String.class);


                            String jobTitle = String.format("Job Type: %s\nDescription: %s\nSalary: %s\nduration: %s\nplace: %s\n", jobType, description, salary, duration, place);
                            jobList.add(new Job(jobTitle));
                        }
                    }
                }

                //set the layout manager, Arrange the items in a one-dimensional list.
                layoutManager = new LinearLayoutManager(EmployeePage.this);
                recyclerView.setLayoutManager(layoutManager);

                jobListAdapter = new JobListAdapter(getApplicationContext(), jobList);
                recyclerView.setAdapter(jobListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void filterList(String query, List<Job> jobList) {


        filteredJobList = new ArrayList<>();

        for (Job job : jobList) {
            if (job.getJobTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredJobList.add(job);
            }
        }

        if(!query.isEmpty()) {
            if (filteredJobList.isEmpty()) {
                // If the filtered list is empty, show a message to the user
                final Toast toast = Toast.makeText(getApplicationContext(), "no match jobs", Toast.LENGTH_SHORT);
                toast.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 888);

            }
        }

        // Update the adapter with the filtered list
        jobListAdapter.setFilteredJobList(filteredJobList);
    }

}