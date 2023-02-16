package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.PatternsCompat;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        Button registerButton = findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(this::onClick);
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected String getEmail() {
        return "";
    }

    protected String getCreditCard() {
        return "";
    }

    protected String getPwd() {
        return "";
    }


    protected boolean validatePwdLength(String pwd) {
        return true;
    }

    protected boolean validatePwdFormat(String pwd) {
        return true;
    }

    protected boolean validatePwdEmptyNull(String pwd) {
        return true;
    }

    protected boolean validateRepeatPwd (String pwd) {return true;}

    protected boolean isValidEmail(String email) {
        return true;
    }


    protected boolean validCCLength(String cc) {
        return true;
    }

    protected boolean validCCFormat(String cc) {
        return true;
    }


    public void onClick (View view) {

    }

}