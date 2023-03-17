/**
 * This activity is used for choosing which become employee and employer
 * Editor: Xinxin Yu
 * Code Reviewer: Zihao Liu
 */

package com.example.quickcash;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

/**
 * This class represents the Landing Page of the application.
 * It contains the logic for checking the user type and redirecting the user to the corresponding page,
 * as well as setting the user type when the user clicks on the Employee or Employer button.
 */
public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        //if user already set his user type,we just jump his page
        checkUserType();
        //click the corresponding button
        EmployeeBtn();
        EmployerBtn();

    }

    /**
     * This method initializes the employee button and sets an onClickListener to change the user type to "Employee".
     * It then starts the EmployeePage activity.
     */
    private void EmployeeBtn() {
        Button employeeBtn = findViewById(R.id.employeeButton);
        String userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        employeeBtn.setOnClickListener(new View.OnClickListener() {
            //when we click the employee button, we jump to the employee page
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Users").child(userID).child("userType").setValue("Employee")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                //jump to the employee_page
                Intent switchIntent = new Intent(LandingPage.this,EmployeePage.class);
                startActivity(switchIntent);
                finish();
            }
        });
    }

    /**
     * This method initializes the employer button and sets an onClickListener to change the user type to "Employer".
     * It then starts the EmployerPage activity.
     */
    private void EmployerBtn() {
        Button employerBtn = findViewById(R.id.employerButton);
        String userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        employerBtn.setOnClickListener(new View.OnClickListener() {
            //when we click the employer button, we jump to the employer page
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Users").child(userID).child("userType").setValue("Employer")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                //jump to the employer_page
                Intent switchIntent = new Intent(LandingPage.this, EmployerPage.class);
                startActivity(switchIntent);
                finish();
            }
        });
    }

    /**
     * This method reads the user type from the Firebase Realtime Database and redirects the user to the corresponding page.
     */
    private void readUserType(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userType = dataSnapshot.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).child("userType").getValue(String.class);
                if(userType.equals("Employee")){
                    Intent switchIntent = new Intent(LandingPage.this,EmployeePage.class);
                    startActivity(switchIntent);
                    finish();
                } else if (userType.equals("Employer")) {
                    Intent switchIntent = new Intent(LandingPage.this,EmployerPage.class);
                    startActivity(switchIntent);
                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     * This method checks if the user has already set their user type in the Firebase Realtime Database.
     * If they have, it redirects them to the corresponding page using the read
     */
    private void checkUserType(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean hasType = dataSnapshot.child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).hasChild("userType");
                if(hasType){
                    readUserType();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }



}