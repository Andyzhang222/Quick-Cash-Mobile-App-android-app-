package com.example.quick_cash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(this);


    }


    protected String getPw() {
        EditText pwTextBox = findViewById(R.id.PasswordTextBox);
        return pwTextBox.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailTextBox = findViewById(R.id.EmailTextBox);
        return emailTextBox.getText().toString().trim();
    }

    protected boolean isEmptyEmail(String email) {
        return email.isEmpty();
    }


    protected boolean isValidEmailAddress(String emailAddress) {
        Pattern pattern = Pattern.compile("^^(.+)@(.+)$",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailAddress);

        if (matcher.matches()){
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        String emailAddress = getEmailAddress();
    }


//    protected boolean isValidEmailAddress(String emailAddress) {
//        //buggy method, fix it.
//        Boolean result = true;
//        Pattern pat = Pattern.compile("^^(.+)@(.+)$",Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pat.matcher(emailAddress);
//
//        if (!matcher.matches()){
//            result = false;
//        }
//        return result;
//    }


}