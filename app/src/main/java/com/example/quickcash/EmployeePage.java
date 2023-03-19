 /**
 * This activity is used for employer activity
 * Editor: Zhiqiang Yu
 */
 package com.example.quickcash;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.quickcash.LocationTracker.LocationTracker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    String preferenceJob;
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
        showSearchView();
        notificationIcon();
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
    }

     private void showSearchView() {
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
                 preferenceJob = dataSnapshot.getValue(String.class);
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
                for (DataSnapshot jobPostID : jobPostSnapshot.getChildren()) {

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


     private void notificationIcon(){
         ImageButton icon = findViewById(R.id.notification_icon);

         DatabaseReference jobPostRef = mDatabaseRef.child("Job Post");
         LocationTracker locationTracker = new LocationTracker(this);
         AtomicReference<String> key = new AtomicReference<>();


         ValueEventListener getListListener= new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 List<String> currentJobID = new ArrayList<>();
                 for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                     String childName = childSnapshot.getKey();
                     currentJobID.add(childName);
                 }
                 ChildEventListener newJobListener = new ChildEventListener() {
                     @Override
                     public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                         locationTracker.startTracking(location -> {
                             String area = locationTracker.getLocalArea(location);
                             boolean samePlace = area.equals(dataSnapshot.child("place").getValue());
                             boolean newJob = !currentJobID.contains(dataSnapshot.getKey());
                             if (samePlace && newJob) {
                                     icon.setImageResource(R.drawable.notification_red_dot);
                                     key.set(dataSnapshot.getKey());
                                 }
                             locationTracker.stopTracking();
                         });

                     }
                     @Override
                     public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                     }

                     @Override
                     public void onChildRemoved(DataSnapshot dataSnapshot) {

                     }

                     @Override
                     public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 };
                 jobPostRef.addChildEventListener(newJobListener);
             }
             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         };
         jobPostRef.addListenerForSingleValueEvent(getListListener);

         icon.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 icon.setImageResource(R.drawable.notification);
                 filterList(key.get(), jobList);
             }
         });



     }


}