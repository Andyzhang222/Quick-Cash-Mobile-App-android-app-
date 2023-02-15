package com.example.quick_cash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    protected boolean isValidPassword(String pw){
        //It contains minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special
        String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*[@_.]).*$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(pw);

        if (!pw.matches(".*\\d.*") || !matcher.matches()) {
            return true;
        }
        return false;
    }

    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLable);
        statusLabel.setText(message.trim());
    }

    @Override
    public void onClick(View view) {
        String emailAddress = getEmailAddress();
        String passWord = getPw();
        String errorMessage = "";

        if (isEmptyEmail(emailAddress)) {
            errorMessage = getResources().getString(R.string.EMPTY_EMAIL).trim();
            setStatusMessage(errorMessage);
        } else if (!isValidEmailAddress(emailAddress)){
            errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim();
            setStatusMessage(errorMessage);
        } else if (!isValidPassword(passWord)){
            errorMessage = getResources().getString(R.string.INVALID_PASSWORD).trim();
            setStatusMessage(errorMessage);
        }

        //if all input are correct, then jump to another activity
        //wait for other member's design...

    }

}