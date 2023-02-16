package com.example.quick_cash;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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


    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected String getEmailAddress() {
        EditText emailTextBox = findViewById(R.id.EmailTextBox);
        return emailTextBox.getText().toString().trim();
    }

    protected boolean isEmptyEmail(String email) {

        return email.isEmpty();
    }

    @Override
    public void onClick(View view) {
//        Toast.makeText(getApplicationContext(),"perform onClick------------------------",Toast.LENGTH_LONG).show();

        String emailAddress = getEmailAddress();
        String passWord = getPw();
        String errorMessage = new String();
//        Toast.makeText(getApplicationContext(),"1111-----("+emailAddress+")------",Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),"222-----("+isEmptyEmail(emailAddress)+")------",Toast.LENGTH_SHORT).show();

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

//        Toast.makeText(getApplicationContext(),"emailAddress-----("+errorMessage+")------",Toast.LENGTH_LONG).show();


        //if all input are correct, then jump to another activity
        //wait for other member's design...

    }

}