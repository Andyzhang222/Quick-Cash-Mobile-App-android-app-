package com.example.quickcash;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //implement login button
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

    }

    /**
     * This method used to get string of email
     * @return the string of email
     */
    protected String getEmail(){
        EditText emailField = findViewById(R.id.emailTextField);
        return  emailField.getText().toString().trim();
    }

    /**
     * This method used to get string of password
     * @return the string of password
     */
    protected String getPassword(){
        EditText passwordField = findViewById(R.id.passwordTextField);
        return  passwordField.getText().toString().trim();
    }


    /**
     *
     * @param message error
     */
    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    @Override
    public void onClick(View v) {
        String email = getEmail();
        String password = getPassword();
        String errorMessage = password;

        if(email.isEmpty()&&password.isEmpty()){
            errorMessage = getResources().getString(R.string.EMPTY_All).trim();
        } else if(email.isEmpty()){
            errorMessage = getResources().getString(R.string.EMPTY_EMAIL).trim();
        } else if (password.isEmpty()) {
            errorMessage = getResources().getString(R.string.EMPTY_PASSWORD).trim();
        }
        setStatusMessage(errorMessage);


    }
}