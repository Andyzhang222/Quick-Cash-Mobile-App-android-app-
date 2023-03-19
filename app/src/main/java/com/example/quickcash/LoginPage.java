/**
 * This activity is used for login
 * Editor: Zhiqiang Yu, Xinxin Yu, Zihao Liu
 * Code Reviewer: Ziyue Wang, Qianrong Yang
 */

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
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;

    /**
     * This method is create a login page with a login button and a register link.
     * Connects to the Firebase database to allow users authenticate and access the application
     * @param savedInstanceState save the instance state of the activity
     */
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        //connect to the firebase
        mAuth = FirebaseAuth.getInstance();

        //implement login button
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);

        //this is th register link for transfer to the register page
        TextView registerLink = findViewById(R.id.registerLink);
        registerLink.setOnClickListener(view -> {
            Intent intent = new Intent(LoginPage.this,RegisterPage.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * This method used to get string of email
     * @return the string of email
     */
    protected String getEmail () {
        EditText emailField = findViewById(R.id.emailTextField);
        return  emailField.getText().toString().trim();
    }



    /**
     * This method used to get string of password
     * @return the string of password
     */
    protected String getPassword () {
        EditText passwordField = findViewById(R.id.passwordTextField);
        return  passwordField.getText().toString().trim();
    }

    /**
     * This method used to check the format of email
     * @param emailAddress the string of email
     * @return true if the format of email is correct,otherwise false
     */
    protected boolean isValidEmailAddress (String emailAddress) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailAddress);

        return matcher.matches();
    }

    /**
     * This method used to check the format of password
     * @param pw the string of password
     * @return true if the format of password is correct,otherwise false
     */
    protected boolean isValidPassword (String pw){
        //It contains minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special
        String passwordPattern = "^(?=.*[A-Z])(?=.*[@_.])(?=.*\\d).{8,}$";
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(pw);

        return matcher.matches();
    }

    /**
     * This method used to give a hint for user input
     * @param message hint for user input
     */
    protected void setStatusMessage (String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    /**
     * This method is used for login
     * @param email user's email for login
     * @param password user's password for login
     */
    protected void login (String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(LoginPage.this,LandingPage.class);
                        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        intent.putExtra("userId", userId);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginPage.this, "Login failed.\nPlease re-enter the correct email and password.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    /**
     * This method that check if the register's email and password empty or not and valid or not
     * @param view the email & password that read from register format
     */
    @Override
    public void onClick (View view) {
        //get email and password
        String email = getEmail();
        String password = getPassword();

        //this part for checking the error of user input
        //if user input is all fine,we should start login
        if (email.isEmpty()&&password.isEmpty()) {
            setStatusMessage(getResources().getString(R.string.EMPTY_All).trim());
        }
        else if (email.isEmpty()) {
            setStatusMessage(getResources().getString(R.string.EMPTY_EMAIL).trim());
        }
        else if (password.isEmpty()) {
            setStatusMessage(getResources().getString(R.string.EMPTY_PASSWORD).trim());
        }
        else if (!isValidEmailAddress(email)) {
            setStatusMessage(getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim());
        }
        else if (!isValidPassword(password)) {
            setStatusMessage(getResources().getString(R.string.INVALID_PASSWORD).trim());
        }
        else {
            setStatusMessage(getResources().getString(R.string.LOGIN_NOW).trim());
            login(email,password);
        }
    }

    /**
     * This part is for check if the user is logged in
     * If yes,the user should direct to the landing page
     */
    @Override
    public void onStart () {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent i = new Intent(getApplicationContext(),LandingPage.class);
            startActivity(i);
            finish();
        }
    }
}
