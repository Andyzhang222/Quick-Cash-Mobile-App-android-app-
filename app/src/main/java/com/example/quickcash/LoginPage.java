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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        mAuth = FirebaseAuth.getInstance();

        //implement login button
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        TextView registerLink = findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,RegisterPage.class);
                startActivity(intent);
                finish();
            }
        });

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

    protected boolean isValidEmailAddress(String emailAddress) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailAddress);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    protected boolean isValidPassword(String pw){
        //It contains minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special
        String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@_.])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(pw);

        if (matcher.matches()) {
            return true;
        }
        return false;
    }


    /**
     * This method used to give a hint for user input
     * @param message hint for user input
     */
    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    /**
     * This method is used for login
     * @param email user's email for login
     * @param password user's password for login
     */
    protected void login(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginPage.this,LandingPage.class);
                            startActivity(intent);
                            finish();
                        } else {

                            Toast.makeText(LoginPage.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        String email = getEmail();
        String password = getPassword();

        if(email.isEmpty()&&password.isEmpty()){
            setStatusMessage(getResources().getString(R.string.EMPTY_All).trim());
        } else if(email.isEmpty()){
            setStatusMessage(getResources().getString(R.string.EMPTY_EMAIL).trim());
        } else if (password.isEmpty()) {
            setStatusMessage(getResources().getString(R.string.EMPTY_PASSWORD).trim());
        } else if (!isValidEmailAddress(email)){
            setStatusMessage(getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim());
        } else if (!isValidPassword(password)){
            setStatusMessage(getResources().getString(R.string.INVALID_PASSWORD).trim());
        } else{
            setStatusMessage(getResources().getString(R.string.LOGIN_NOW).trim());
            login(email,password);
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(getApplicationContext(),LandingPage.class);
            startActivity(i);
            finish();
        }
    }
}