/**
 * This activity is used for choosing which become employee and employer
 * Editor: Xinxin Yu
 */

package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.firebase.database.*;
import androidx.annotation.NonNull;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        //click the corresponding button
        Button employeeBtn = findViewById(R.id.employeeButton);
        Button employerBtn = findViewById(R.id.employerButton);

        employeeBtn.setOnClickListener(new View.OnClickListener() {
            //when we click the employee button, we jump to the employee page
            @Override
            public void onClick(View v) {
                //jump to the employee_page
                Intent switchIntent = new Intent(LandingPage.this,EmployeePage.class);
                startActivity(switchIntent);
            }
        });

        employerBtn.setOnClickListener(new View.OnClickListener() {
            //when we click the employer button, we jump to the employer page
            @Override
            public void onClick(View v) {
                //jump to the employer_page
                Intent switchIntent = new Intent(LandingPage.this, EmployerPage.class);
                startActivity(switchIntent);
            }
        });

    }
}