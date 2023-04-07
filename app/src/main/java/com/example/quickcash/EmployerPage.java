/**
 * This activity is used for employer activity
 * Editor: Zhiqiang Yu
 */

package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.quickcash.Explore.ExploreEmployerActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class EmployerPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_page);

        //implement the log out button function
        //when user click the button, we should log out
        //After that we redirect to the login page
        Button logoutBtn = findViewById(R.id.LogoutButton2);
        Button postBtn = findViewById(R.id.post);
        Button checkPostedJob = findViewById(R.id.checkPostedJob);
        logoutBtn.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(),LoginPage.class);
            startActivity(intent);
            finish();
        });


        postBtn.setOnClickListener(v -> {
            Intent switchIntent = new Intent(getApplicationContext(),SubmitJobsActivity.class);
            startActivity(switchIntent);
            finish();
        });

        checkPostedJob.setOnClickListener(v -> {
            Intent intent = new Intent(EmployerPage.this, ViewJobsActivity.class);
            startActivity(intent);
        });


        exploreButton();
    }

    /**
     * Sets up the functionality of the explore button on the EmployeePage activity.
     */
    private void exploreButton() {
        Button btnExplore = findViewById(R.id.btn_explore);
        btnExplore.setOnClickListener(v -> {
            Intent intent = new Intent(EmployerPage.this, ExploreEmployerActivity.class);
            startActivity(intent);
        });
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