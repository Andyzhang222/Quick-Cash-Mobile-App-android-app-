/**
 * This activity is used for employer activity
 * Editor: Zhiqiang Yu
 */

package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class EmployerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_page);
        //connect to the firebase
        FirebaseAuth auth = FirebaseAuth.getInstance();

        //implement the log out button function
        //when user click the button, we should log out
        //After that we redirect to the login page
        Button logoutBtn = findViewById(R.id.LogoutButton2);
        Button postBtn = findViewById(R.id.post);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();;
                Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();
            }
        });


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FirebaseAuth.getInstance().signOut();;
                Intent switchIntent = new Intent(getApplicationContext(),SubmitJobsActivity.class);
                String userId = getIntent().getStringExtra("userId");
                switchIntent.putExtra("userId", userId);
                //test for if get the id
                Toast.makeText(EmployerPage.this, "-------------------"+userId, Toast.LENGTH_SHORT).show();
                startActivity(switchIntent);
                finish();
            }
        });




    }
}