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

import com.example.quickcash.Explore.ExploreEmployeeActivity;
import com.example.quickcash.LocationTracker.LocationTracker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

 //Used S of the SOLID Principle
public class EmployeePage extends AppCompatActivity{
     public static final String JOB_POST = "Job Post";
     public static final String PLACE = "place";
     private DatabaseReference mDatabaseRef;

    Button saveButton;
    RecyclerView recyclerView;
    SearchView searchView;
    List<Job> jobList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    JobListAdapter jobListAdapter;
    List<Job> filteredJobList;

    FirebaseAuth auth;
    String preferenceJob = " ";


    //String userId;

     String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_page);

        // Initialize the Firebase database reference
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        //connect to the firebase
        auth = FirebaseAuth.getInstance();

        //implement the logout button function
        logoutBtn();
        showSearchView();
        notificationIcon();

        exploreButton();
    }

     /**
      * Sets up the functionality of the explore button on the EmployeePage activity.
      */
     private void exploreButton() {
         Button btnExplore = findViewById(R.id.btn_explore);
         btnExplore.setOnClickListener(v -> {
             Intent intent = new Intent(EmployeePage.this, ExploreEmployeeActivity.class);
             startActivity(intent);
         });
     }

     /**
     * This method used to implement the logout button function
     */
    private void logoutBtn() {
        Button logoutBtn = findViewById(R.id.LogoutButton1);
        //when user click the button, we should log out
        //After that we redirect to the login page
        View.OnClickListener logoutListener = v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginPage.class);
            startActivity(intent);
            finish();
        };
        logoutBtn.setOnClickListener(logoutListener);
    }

     /**
      * Displays the search view for the user to search for jobs.
      * Initializes the RecyclerView with a list of jobs and sets up the search functionality.
      * Allows the user to save their search preference to Firebase and loads their preference if they have one.
      */
     private void showSearchView() {
         //sample list, delete later
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
         View.OnClickListener save = view -> {
             //get input
             String searchText = searchView.getQuery().toString();

             //get user path
             DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");

             //get id
             String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

             //System.out.println("=================================" + userId);

             userRef.child(userId).child("preference").setValue(searchText);
             preferenceJob = searchText;

             Toast.makeText(EmployeePage.this, "Search text saved successfully", Toast.LENGTH_SHORT).show();
         };
         saveButton.setOnClickListener(save);
         //get user path
         DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users");

         //get current user id to get their preference
         String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

         if (userRef.child(userId).child("preference") != null) {
             //get preference data
             userRef.child(userId).child("preference").addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     // Retrieve the preference data and do something with it
                     String preference = dataSnapshot.getValue(String.class);
                     if (preference != null) {
                         preferenceJob = preference;
                     }
                     searchView.setQuery(preference, false); // Set the search bar text to the preference data
                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {
                     // Handle errors here
                 }
             });
         }
     }

     /**
      * This method fills the job list by retrieving all "Open" job posts from the database and creating a job object for each
      * one. It then sets the layout manager and job list adapter for the RecyclerView to display the jobs.
      */
     public void fillJobList () {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference jobPostRef = rootRef.child(JOB_POST);

        jobPostRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot jobPostSnapshot) {
                for (DataSnapshot jobPostID : jobPostSnapshot.getChildren()) {


                    String status = jobPostID.child("status").getValue(String.class);
                    if (status.equals("Open")) {

                        System.out.println("=================================" + userId);


                        String jobId = jobPostID.getKey();

                        String jobType = jobPostID.child("jobType").getValue(String.class);
                        String description = jobPostID.child("description").getValue(String.class);
                        String salary = jobPostID.child("salary").getValue().toString();
                        String duration = jobPostID.child("duration").getValue().toString();
                        String place =  jobPostID.child(PLACE).getValue(String.class);
                        String jobTitle = String.format("Job Type: %s\nDescription: %s\nSalary: %s\nduration: %s\nplace: %s\n", jobType, description, salary, duration, place);

                        Job job = new Job(jobTitle,"");
                        job.setJobId(jobId);

                        jobList.add(job);
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
                //We don't need to control this situation
            }
        });
    }

     /**
      * This method filters the job list based on the user's search query. It creates a new filtered list and adds only the jobs
      * that match the search query. If the query is empty or there are no matches, it displays a message to the user for a short
      * period of time. Finally, it updates the job list adapter with the filtered list.
      * @param query A string representing the user's search query
      * @param jobList A list of all jobs to be filtered
      */
    private void filterList(String query, List<Job> jobList) {


        filteredJobList = new ArrayList<>();

        for (Job job : jobList) {
            if (job.getJobTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredJobList.add(job);
                //System.out.println("=================================" + userId);
            }
        }

        if(!query.isEmpty()&&filteredJobList.isEmpty()) {

                // If the filtered list is empty, show a message to the user
                final Toast toast = Toast.makeText(getApplicationContext(), "no match jobs", Toast.LENGTH_SHORT);
                toast.show();

                Handler handler = new Handler();
            Runnable runner = toast::cancel;
            handler.postDelayed(runner, 888);


        }

        // Update the adapter with the filtered list
        jobListAdapter.setFilteredJobList(filteredJobList);
    }


     /**
      * This method sets up the notification icon and its functionality. It tracks the user's location and compares it to the
      * location of new job postings in the database. If a new job posting is in the user's preferred location and matches their
      * job preference, the notification icon will display a red dot. When the icon is clicked, it resets to its default state
      * and filters the job list to show only jobs that match the user's preference.
      */
     private void notificationIcon(){
         ImageButton icon = findViewById(R.id.notification_icon);

         DatabaseReference jobPostRef = mDatabaseRef.child(JOB_POST);
         LocationTracker locationTracker = new LocationTracker(this);


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

                             String jobId = dataSnapshot.getKey();

                             //
                             String employeeid = dataSnapshot.child("employeeId").getValue(String.class);

                             System.out.println("--------------------------------"+ employeeid);

                             String area = locationTracker.getLocalArea(location);
                             String jobType = dataSnapshot.child("jobType").getValue(String.class);
                             String description = dataSnapshot.child("description").getValue(String.class);
                             String salary = dataSnapshot.child("salary").getValue().toString();
                             String duration = dataSnapshot.child("duration").getValue().toString();
                             String place = dataSnapshot.child(PLACE).getValue(String.class);
                             String jobTitle = String.format("Job Type: %s\nDescription: %s\nSalary: %s\nduration: %s\nplace: %s\n", jobType, description, salary, duration, place);

                             Job newJob = new Job(jobTitle,"");
                             newJob.setJobId(jobId);

                             jobList.add(newJob);
                             boolean samePlace = area.equals(dataSnapshot.child(PLACE).getValue());
                             boolean isNewJob = !currentJobID.contains(dataSnapshot.getKey());
                             boolean contains = jobTitle.toLowerCase().contains(preferenceJob);
                             if (samePlace && isNewJob && contains){
                                     icon.setImageResource(R.drawable.notification_red_dot);

                             }
                             locationTracker.stopTracking();
                         });

                     }
                     @Override
                     public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                         //We don't need to control this situation
                     }
                     @Override
                     public void onChildRemoved(DataSnapshot dataSnapshot) {
                         //We don't need to control this situation
                     }

                     @Override
                     public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                         //We don't need to control this situation
                     }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {
                         //We don't need to control this situation
                     }
                 };
                 jobPostRef.addChildEventListener(newJobListener);
             }
             @Override
             public void onCancelled(DatabaseError databaseError) {
                //We don't need to control this situation
             }
         };
         jobPostRef.addListenerForSingleValueEvent(getListListener);

         View.OnClickListener iconListener = v -> {
             icon.setImageResource(R.drawable.notification);
             filterList(preferenceJob, jobList);
         };
         icon.setOnClickListener(iconListener);

     }


     /**
      * This method is called when the activity is starting. It checks if the current user has set a username.
      * If the username is not set, it displays the UsernameDialog for the user to set their username.
      */
     @Override
     protected void onStart() {
         super.onStart();
         UsernameDialog.checkIfUsernameSet(this, username -> {
             FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
             if (currentUser != null) {
                 String userId = currentUser.getUid();
                 UsernameDialog.setUserUsername(userId, username);
             }
         });
     }
 }